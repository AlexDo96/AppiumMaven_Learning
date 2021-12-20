package models.components.webview;

public class MenuItem {
    private String text;
    private String hyperLink;

    public MenuItem(String text, String hyperLink) {
        this.text = text;
        this.hyperLink = hyperLink;
    }

    public String text() {
        return text;
    }

    public String hyperLink() {
        return hyperLink;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "text='" + text + '\'' +
                ", hyperLink='" + hyperLink + '\'' +
                '}';
    }
}
