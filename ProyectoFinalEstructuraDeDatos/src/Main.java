import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.*;

public class Main {
    private static int validar = 0;
    private static String[] cadenaschar;
    private static int[] enteros;
    private static Object[] datos;
    private static String[][] matrizresultante;
    private static Lista listas;

    public static void main(String[] args) {
        //menu interfaz grafica para el usuario
        while (true) {
            Object[] options = {
                "Leer archivo de texto y Pasar datos del archivo de texto a un array",
                "Conversión del arreglo a una matriz estática",
                "Conversión de matriz estática a lista enlazada",
                "Conversión de lista enlazada a un árbol",
                "Salir"
            };
            // variable que guarda la eleccion del usuario
            int choice = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == -1 || choice == 4) {
                break;
            }
//casos de eleccion del usuario
            switch (choice) {
                case 0:
                // datos guardara lo que retorne la funcion
                    datos = leerArchivoYGuardarEnArreglos();
                    //si no es null en cadenaschar guardara las filas del objeto que retorno la funcion y en las columnas los strings
                    if (datos != null) {
                        cadenaschar = (String[]) datos[0];
                        enteros = (int[]) datos[1];
//mostrar el arreglo de cadenaschar
                        System.out.println("cadenaschar:");
                        for (String cadena : cadenaschar) {
                            System.out.println(cadena);
                        }
//mostrar el arreglo de enteros
                        System.out.println("Enteros:");
                        for (int entero : enteros) {
                            System.out.println(entero);
                        }
                        validar = 1;
                        JOptionPane.showMessageDialog(null, "Archivo leído y arreglos hechos con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo leer el archivo o está vacío.");
                    }
                    break;
                case 1:
                    if (cadenaschar != null && enteros != null) {
                        matrizresultante = conversionamatriz(cadenaschar, enteros);
                        JOptionPane.showMessageDialog(null, "Conversión a matriz estática realizada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ningún arreglo para hacer su conversión a matriz.");
                    }
                    break;
                case 2:
                    try {
                        if (matrizresultante != null) {
                            Object[] options2 = {
                                "Lista simple",
                                "Lista doble",
                                "Lista circular",
                                "Salir"
                            };
                            int choice2 = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options2, options2[0]);

                            if (choice2 == -1 || choice2 == 3) {
                                break;
                            }

                            switch (choice2) {
                                case 0:
                                    if (validar == 1) {
                                        listas = matrizalista(matrizresultante, 1);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No hay datos disponibles para hacer la conversión.");
                                    }
                                    break;
                                case 1:
                                    if (validar == 1) {
                                        listas = matrizalista(matrizresultante, 2);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No hay datos disponibles para hacer la conversión.");
                                    }
                                    break;
                                case 2:
                                    if (validar == 1) {
                                        listas = matrizalista(matrizresultante, 3);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No hay datos disponibles para hacer la conversión.");
                                    }
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay matriz disponible para realizar la operación.");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No hay matriz disponible para realizar la operación.");
                    }
                    break;
                case 3:
                    if (validar == 1 && listas != null) {
                        Arbol arbolito = createBinaryarbolito(listas);
                        if (arbolito != null) {
                            Object[] options3 = {
                                "in-order",
                                "pre-order",
                                "post-order"
                            };
                            int choice3 = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options3, options3[0]);

                            if (choice3 == -1 || choice3 == 3) {
                                break;
                            }

                            switch (choice3) {
                                case 0:
                                System.out.println("---------------------------IN ORDER---------------------------");
                                    inOrderTraversal(arbolito);
                                    break;
                                case 1:
                                System.out.println("---------------------------PRE ORDER---------------------------");
                                    preOrderTraversal(arbolito);
                                    break;
                                case 2:
                                System.out.println("---------------------------POST ORDER---------------------------");
                                    postOrderTraversal(arbolito);
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo crear el árbol con los datos proporcionados.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay datos disponibles para hacer la conversión.");
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    public static String[][] conversionamatriz(String[] arreglo1, int[] arreglo2) {
        int numRows = Math.max(arreglo1.length, arreglo2.length);
        int numCols = 2;
        String[][] conversionamatriz = new String[numRows][numCols];

        for (int i = 0; i < arreglo2.length; i++) {
            conversionamatriz[i][0] = String.valueOf(arreglo2[i]);
        }
        for (int i = 0; i < arreglo1.length; i++) {
            conversionamatriz[i][1] = arreglo1[i];
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(conversionamatriz[i][j] + "\t");
            }
            System.out.println();
        }

        return conversionamatriz;
    }

    public static Object[] leerArchivoYGuardarEnArreglos() {
        FileReader archivo;
        BufferedReader lector;
        ArrayList<String> listacadenaschar = new ArrayList<>();
        ArrayList<Integer> listaEnteros = new ArrayList<>();

        try {
            String nombreArchivo = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo de texto:");
            archivo = new FileReader(nombreArchivo);
            
            if (archivo.ready()) {
                lector = new BufferedReader(archivo);
                String cadena;

                while ((cadena = lector.readLine()) != null) {
                    try {
                        int numero = Integer.parseInt(cadena);
                        listaEnteros.add(numero);
                    } catch (NumberFormatException e) {
                        listacadenaschar.add(cadena);
                    }
                }

                lector.close();
               
            } else {
                JOptionPane.showMessageDialog(null, "Archivo no encontrado.");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return null;
        }
//de las arraylist pasamos a unos arreglos estaticos con dimensones dependientes de los arraylist
        String[] arreglocadenaschar = new String[listacadenaschar.size()];
        arreglocadenaschar = listacadenaschar.toArray(arreglocadenaschar);

        int[] arregloEnteros = new int[listaEnteros.size()];
        for (int i = 0; i < listaEnteros.size(); i++) {
            arregloEnteros[i] = listaEnteros.get(i);
        }

        return new Object[]{arreglocadenaschar, arregloEnteros};
    }



    public static Lista matrizalista(String[][] conversionamatriz, int eleccion) {
        Lista list = new Lista();
        for (String[] row : conversionamatriz) {
            if (row.length >= 2) {
                String numString = row[0].trim();
                String description = row[1].trim();
                try {
                    int number = Integer.parseInt(numString);
                    switch (eleccion) {
                        case 1:
                            list.addsimple(number, description);
                            System.out.println("-------------------------------------------------");
                            list.display();
                            break;
                        case 2:
                            list.add_double(number, description);
                            System.out.println("Recorrido normal de la lista");
                            list.display();
                            System.out.println("Recorrido doble");
                            list.display_backward();
                            break;
                        case 3:
                            list.add_circular(number, description);
                            list.display_circular();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: No se pudo convertir '" + numString + "' a entero.");
                }
            } else {
                System.out.println("Error: Fila de la matriz incompleta.");
            }
        }

        return list;
    }
    public static void preOrderTraversal(Arbol root) {
        
        if (root != null) {
            System.out.println(" " + root.id + " : " + root.description);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
        
    }
    public static Arbol createBinaryarbolito(Lista lista) {
        if (lista == null || lista.head == null) {
            return null;
        }
        Arbol root = new Arbol(lista.head.id, lista.head.description);
        Nodo current = lista.head.next;

        while (current != null) {
            insertaralarbolito(root, current.id, current.description);
            current = current.next;
        }
        JOptionPane.showMessageDialog(null,"Arbol binario de busqueda creado exitosamente");
        return root;
    }
    public static void inOrderTraversal(Arbol root) {
        
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(" " + root.id + " : " + root.description);
            inOrderTraversal(root.right);
        }
       
    }

    private static void insertaralarbolito(Arbol root, int id, String description) {
        if (id < root.id) {
            if (root.left == null) {
                root.left = new Arbol(id, description);
            } else {
                insertaralarbolito(root.left, id, description);
            }
        } else {
            if (root.right == null) {
                root.right = new Arbol(id, description);
            } else {
                insertaralarbolito(root.right, id, description);
            }
        }
    }

   

    

    public static void postOrderTraversal(Arbol root) {
        
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(" : " + root.id + " : " + root.description);
        }
        
    }
}