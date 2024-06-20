class Lista {
    Nodo head;

    // Agregar nodo a lista simple
    public void addsimple(int id, String description) {
        Nodo newNodo = new Nodo(id, description);
        if (head == null) {
            head = newNodo;
        } else {
            Nodo current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNodo;
        }
    }

    // Agregar nodo a lista doble
    public void add_double(int id, String description) {
        Nodo newNode = new Nodo(id, description);
        if (head == null) {
            head = newNode;
        } else {
            Nodo current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
    }

    // Agregar nodo a lista circular
    public void add_circular(int id, String description) {
        Nodo newNode = new Nodo(id, description);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Nodo tail = head.prev;
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
    }

    // Mostrar lista simple
    public void display() {
        System.out.println(" ------------------------lista simple -----------------------------------------------------");
        Nodo current = head;
        while (current != null) {
            System.out.println("ID: " + current.id + ", description: " + current.description + "->");
            current = current.next;
        }
        System.out.println(" -----------------------------------------------------------------------------");
    }

    // Mostrar lista doble
    public void display_backward() {
        System.out.println(" -------------------------------lista doble----------------------------------------------");
        if (head == null) return;
        Nodo current = head;
        while (current.next != null) {
            current = current.next;
        }
        while (current != null) {
            System.out.println("ID: " + current.id + ", description: " + current.description);
            current = current.prev;
        }
        System.out.println(" -----------------------------------------------------------------------------");
    }

    // Mostrar lista circular
    public void display_circular() {
        System.out.println(" -----------------------------------lista circular------------------------------------------");
        if (head != null) {
            Nodo current = head;
            do {
                System.out.println("ID: " + current.id + ", description: " + current.description + "->");
                current = current.next;
            } while (current != head);
        }
        System.out.println(" -----------------------------------------------------------------------------");
    }
}
