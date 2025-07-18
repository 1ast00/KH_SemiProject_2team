package view;

public class ModelAndView {

    private String path;
    private boolean redirect;

    
    public ModelAndView() {
        this.path = null;
        this.redirect = false;
    }

    
    public ModelAndView(String path) {
        this.path = path;
        this.redirect = false;
    }

    
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
