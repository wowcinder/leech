package com.voole.leech.gwt.client.common.editor;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.common.LinkGwtCallBack;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.common.event.EditEvent.EditHanlder;
import com.voole.leech.gwt.client.common.validator.SimpleCinderValidator;
import com.voole.leech.gwt.client.common.window.FixedWindow;

public abstract class CinderEditor<T> extends Widget implements Editor<T>,
		EditHanlder<T> {
	protected static final VerticalLayoutData vd = new VerticalLayoutData(1, -1);
	protected static final VerticalLayoutData mainVd = new VerticalLayoutData(
			1, 1);

	@Ignore
	private final FixedWindow root;
	@Ignore
	protected final FormPanel formPanel;
	@Ignore
	protected final VerticalLayoutContainer layoutContainer;
	@Ignore
	private final TextButton saveOrUpdateBt;
	@Ignore
	private final TextButton cancelBt;
	@Ignore
	private String baseHeadingText = "";
	@Ignore
	private EditEvent<T> currEditEvent;
	@Ignore
	private final SimpleBeanEditorDriver<T, ? extends CinderEditor<T>> driver;

	public CinderEditor(
			SimpleBeanEditorDriver<T, ? extends CinderEditor<T>> driver,
			String baseHeadingText) {
		this.driver = driver;
		this.baseHeadingText = baseHeadingText;

		root = new FixedWindow();
		formPanel = new FormPanel();
		layoutContainer = new VerticalLayoutContainer();

		formPanel.getElement().setPadding(new Padding(10));
		formPanel.setBorders(true);
		formPanel.setWidget(layoutContainer);
		root.setWidget(formPanel);

		saveOrUpdateBt = new TextButton("Save");
		cancelBt = new TextButton("取消");
		root.setButtonAlign(BoxLayoutPack.END);
		root.setModal(true);

		root.addButton(saveOrUpdateBt);
		root.addButton(cancelBt);

		cancelBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				CinderEditor.this.cancel();
			}
		});

		getRoot().setCancelBack(new GwtCallBack<SelectEvent>() {
			@Override
			public void _call(SelectEvent t) {
				CinderEditor.this.cancel();
			}
		});

		saveOrUpdateBt.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				getSaveOrUpdateBt().disable();
				saveOrUpdate();
			}
		});

		addHandler(this, EditEvent.getType());

		initView();
		getDriver().initialize(this);
	}

	protected void postSaveOrUpdate() {
		getSaveOrUpdateBt().enable();
	}

	protected void update(T t) {
		if (!SimpleCinderValidator.Instance.validate(t)) {
			postSaveOrUpdate();
			return;
		}
		_update(t);
	}

	protected void add(T t) {
		if (!SimpleCinderValidator.Instance.validate(t)) {
			postSaveOrUpdate();
			return;
		}
		_add(t);
	}

	protected abstract void _update(T t);

	protected abstract void _add(T t);

	protected abstract void _initView();

	protected T flush() {
		return getDriver().flush();
	}

	protected void saveOrUpdate() {
		T t = flush();
		if (getCurrEditEvent().isUpdate()) {
			update(t);
		} else {
			add(t);
		}
	}

	protected GwtCallBack<T> getLinkGwtCallBack() {
		return new LinkGwtCallBack<T, T>(getCurrEditEvent().getCallback()) {
			@Override
			protected void _call(T t) {
				_swapperCall(t);
				getRoot().hide();
			}

			@Override
			protected void post() {
				postSaveOrUpdate();
				super.post();
			}
		};
	}

	protected AsyncCallback<T> getSaveOrUpdateAsyncCallback() {
		return RpcAsyncCallback.dealWith(getLinkGwtCallBack());
	}

	protected void initView() {
		_initView();
		getRoot().forceLayout();
	}

	protected void cancel() {
		getCurrEditEvent().getCallback().cancel();
		getRoot().hide();
	}

	@Override
	public Widget asWidget() {
		return root;
	}

	@Override
	public void onEdit(EditEvent<T> event) {
		this.currEditEvent = event;
		refreshTextInfo();
		getDriver().edit(event.getTarget());
		getRoot().show();
	}

	protected void refreshTextInfo() {
		getRoot().setHeadingText(getHeadingText());
		if (getCurrEditEvent().isUpdate()) {
			getSaveOrUpdateBt().setText("修改");
		} else {
			getSaveOrUpdateBt().setText("添加");
		}
	}

	protected String getHeadingText() {
		if (!getCurrEditEvent().isUpdate()) {
			return "添加  " + getBaseHeadingText();
		}
		return "修改  " + getBaseHeadingText();
	}

	@Ignore
	public FixedWindow getRoot() {
		return root;
	}

	public String getBaseHeadingText() {
		return baseHeadingText;
	}

	public void setBaseHeadingText(String baseHeadingText) {
		this.baseHeadingText = baseHeadingText;
	}

	public EditEvent<T> getCurrEditEvent() {
		return currEditEvent;
	}

	public void setCurrEditEvent(EditEvent<T> currEditEvent) {
		this.currEditEvent = currEditEvent;
	}

	@SuppressWarnings("unchecked")
	public SimpleBeanEditorDriver<T, CinderEditor<T>> getDriver() {
		return (SimpleBeanEditorDriver<T, CinderEditor<T>>) driver;
	}

	@Ignore
	public TextButton getSaveOrUpdateBt() {
		return saveOrUpdateBt;
	}

	@Ignore
	public TextButton getCancelBt() {
		return cancelBt;
	}

	public void fireEditEvent(EditEvent<T> event) {
		fireEvent(event);
	}

}
