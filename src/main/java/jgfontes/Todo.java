package jgfontes;

public record Todo(String id, String title, String description, boolean done) {

    public Todo{
        if(title == null || title.length() < 3) {
            throw new IllegalArgumentException("A title bigger than 3 characters is needed");
        }
    }
}
