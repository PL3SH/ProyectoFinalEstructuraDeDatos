import javax.swing.*;
class Arbol {
    int id;
    String description;
    Arbol left, right;

    Arbol(int id, String description) {
        this.id = id;
        this.description = description;
    }
}