package com.voole.leech.gwt.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface CenterView extends IsWidget {

	public static class CenterViewConfig {
		private String label = "OVERVIEW";
		private boolean closeAble = true;
		private String token;

		public CenterViewConfig(String token, String label) {
			this.token = token;
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public boolean isCloseAble() {
			return closeAble;
		}

		public void setCloseAble(boolean closeAble) {
			this.closeAble = closeAble;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
	public CenterViewConfig getCenterViewConfig();

	public void setCenterViewConfig(CenterViewConfig centerViewConfig);
}
