package view;

public class ModelAndView {
	// 이동할 페이지 경로
	private String path;
	// 이동시 redirect? forward인지 결정하는 필드
	private boolean redirect;

	public ModelAndView(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	@Override
	public String toString() {
		return "ModelAndView [path=" + path + ", redirect=" + redirect + "]";
	}
}