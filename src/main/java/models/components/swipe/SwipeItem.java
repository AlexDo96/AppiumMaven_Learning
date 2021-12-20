package models.components.swipe;

public class SwipeItem {
    private String description;

    public SwipeItem(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    @Override
    public String toString() {
        return "SwipeItem{" +
                ", description='" + description + '\'' +
                '}';
    }
}
