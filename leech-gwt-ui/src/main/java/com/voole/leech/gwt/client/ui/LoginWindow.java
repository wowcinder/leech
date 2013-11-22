package com.voole.leech.gwt.client.ui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.window.FixedWindow;
import com.voole.leech.gwt.client.util.RpcServiceUtils;

public class LoginWindow extends FixedWindow {

	public interface LoginSucessCallBack {
		void logined();
	}

	protected static final VerticalLayoutData vd = new VerticalLayoutData(1, -1);
	private FormPanel fp = new FormPanel();
	private VerticalLayoutContainer vc = new VerticalLayoutContainer();
	private TextField email;
	private PasswordField password;
	private TextButton loginBt;

	private LoginSucessCallBack callback;
	private boolean isLoginIng = false;

	public LoginWindow() {
		setModal(true);
		setHeadingText("登录");

		email = new TextField();
		password = new PasswordField();
		loginBt = new TextButton("Login");

		// TODO 测试用
		email.setValue("admin");
		password.setValue("admin");

		fp.getElement().setPadding(new Padding(10));
		fp.setBorders(true);

		fp.setWidget(vc);

		forceLayout();
		setWidget(fp);

		setButtonAlign(BoxLayoutPack.CENTER);
		addButton(loginBt);

		email.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					login();
				}
			}
		});

		password.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					login();
				}
			}
		});

		loginBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				login();
			}
		});
		init();
	}

	private void init() {
		vc.add(new FieldLabel(email, "邮箱"), vd);
		vc.add(new FieldLabel(password, "密码"), vd);
	}

	private void login() {
		if (isLoginIng) {
			return;
		}
		isLoginIng = true;
		String emailV = email.getCurrentValue();
		String passwordV = password.getCurrentValue();
		RpcServiceUtils.OpenAuthorizeRpcService.login(emailV, passwordV,
				new RpcAsyncCallback<Boolean>() {
					@Override
					public void _onSuccess(Boolean result) {
						if (result) {
							if (getCallback() != null) {
								getCallback().logined();
							}
							hide();
						} else {
							AlertMessageBox d = new AlertMessageBox("失败",
									"邮箱或密码错误!");
							d.show();
						}
						isLoginIng = false;
					}

					@Override
					public void post() {
						super.post();
						isLoginIng = false;
					}
				});
	}

	public LoginSucessCallBack getCallback() {
		return callback;
	}

	public void setCallback(LoginSucessCallBack callback) {
		this.callback = callback;
	}

}
