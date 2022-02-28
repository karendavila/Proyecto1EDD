/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * La clase Modify se ejecuta a partir del botón
 * de Modificar Grafo, se encarga de toda la parte
 * de restructurar la base de datos a partir de lo 
 * que el usuario desee cambiar. 
 * 
 * @author dario
 */
public class Modify{
    private int[] usersArray;
    private int size;
    private static Grafo g;
    private String idStr; 
    private String user; 
    private int index; 
    private String timeStr; 
    private String id1Str; 
    private String id2Str; 
    private boolean changeMade; 
    private String relsGuide; 
     
    
    
    /**
     * Modify es la que dirige toda la parte de modificar
     * le pregunta al usuario qué desea modificar y si es
     * añadir o eliminar y dirige el programa. Mediante 
     * inputs determina qué desea el usuario y ejecuta las 
     * funciones necesarias para ello. 
     *
     * @param usersArray arreglo de enteros solo con
     *          los id de los usuarios
     * 
     * @param usersGuide mega string de la lista de usuarios
     *          cuyo separador entre líneas es "//"
     * 
     * @param relsGuide mega string de la lista de relaciones
     *          cuyo separador entre líneas es "//"
     * 
     * @param path ruta del txt
     * 
     * @author Darío Aldana
     */
    public Modify(int[] usersArray, String usersGuide, String relsGuide, String path){
        this.changeMade = false; 
        /** changeMade es un booleano que determina si existió
         * algún cambio en los usuarios y las relaciones para
         * después ejecutar el cambio en la base de datos.
         * 
         */
        
        this.usersArray = usersArray;
        this.size = usersArray.length;

        
        // Toda la siguiente parte es de determinar qué
        //acción realizará el usuario. 
        int selection1 = JOptionPane.showOptionDialog(null, 
                        "Indique qué desea modificar", 
                        "Modificar Base de Datos", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, 
                        new Object[] {"Usuarios",
                            "Relaciones"},
                        null);
        
        if (selection1 != -1) {
            if (selection1 == 0){
                int selection2 = JOptionPane.showOptionDialog(null, 
                        "Indique la acción a realizar:", 
                        "Modificar Base de Datos", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, 
                        new Object[] {"Añadir Usuario",
                            "Eliminar Usuario"},
                        null);
                
                if (selection2 != -1){
                    if (selection2==0){
                        usersGuide = addUser(usersGuide);
                        
                        
                    } else if(selection2 == 1){
                        usersGuide = popUser(usersGuide, relsGuide);
                        relsGuide = popUsersRels(idStr, relsGuide);
                    }
                }
                
            } else if(selection1 == 1){
                int selection2 = JOptionPane.showOptionDialog(null, 
                        "Indique la acción a realizar:", 
                        "Modificar Base de Datos", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, 
                        new Object[] {"Añadir Relación",
                            "Eliminar Relación"},
                        null);
                
                if (selection2 != -1){
                    if (selection2==0){
                        relsGuide = addRel(relsGuide);
                    } else if(selection2 == 1){
                        relsGuide = popRel(relsGuide);
                    }
                }
            }        
        }
        
        if (changeMade){
            rewriteDatabase(path, usersGuide, relsGuide);
        }
    }
    
    
    
    
    /**
     * addUser permite añadir un usuario, recibe el mega string
     * de los usuarios y formula un nuevo usuario para luego 
     * concatenarlo con el string. El ID del usuario sólo puede
     * tener tres dígitos y el usuario debe empezar por "@"
     * 
     * @param usersGuide mega txt de la lista de usuarios
     *          cuyo separador entre líneas es "//"
     * 
     * @author Darío Aldana
     * 
     * @return usersGuide el mismo param pero editado
     */
    public String addUser(String usersGuide){
        while (true) {
            try {
                String idStr = JOptionPane.showInputDialog(null, 
                        "Ingrese el ID del usuario a añadir (tres dígitos)", 
                        "Añadir Usuario", 
                        JOptionPane.QUESTION_MESSAGE);

                if (idStr == null){
                    return null;
                }
                
                if (idStr.length() != 3){
                    throw new RuntimeException();
                }
                
                int id = Integer.parseInt(idStr);
                
                for (int i = 0; i < usersArray.length; i++) {
                    if (id == usersArray[i]){
                        throw new Exception();
                    }
                }
                this.idStr = idStr;
                break;
                
            } catch(java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Ingrese un valor válido\n", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                
                }  catch (RuntimeException e){
                    JOptionPane.showMessageDialog(null,
                        "Error:\nLongitud de ID no permitida", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,
                        "Error:\nID de Usuario Ya Añadido", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        
        while (true){
            try{
                String user = JOptionPane.showInputDialog(null, 
                        "Ingrese el usuario a añadir", 
                        "Añadir Usuario", 
                        JOptionPane.QUESTION_MESSAGE); 
                
                if (user == null){
                    return null;
                }
                
                if (user.charAt(0)!= '@'){
                    throw new RuntimeException();
                }
                
                this.user = user; 
                break;
                        
            }catch (RuntimeException r){
                JOptionPane.showMessageDialog(null,
                        "Error:\nEl Usuario debe iniciar con '@'", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
       
        String userToAdd = idStr + ", " + user + "//";
        usersGuide = usersGuide.concat(userToAdd);
        
        this.changeMade = true; 
        return usersGuide;
       }
    
    /**
     * popUser permite borrar un usuario por su ID
     * tiene un ciclo for donde se copia el array de usuarios
     * (lleno de enteros que corresponden a los IDs)
     * pero con una longitud disminuida en uno, de manera que
     * se copien todos los usuarios menos el que se busca 
     * eliminar y esa versión final es la que se convierte
     * en string de nuevo para posteriormente modificar la 
     * base de datos
     * 
     * @param usersGuide mega string de la lista de usuarios
     *          cuyo separador entre líneas es "//"
     * 
     * @param relsGuide mega string de la lista de relaciones
     *          cuyo separador entre líneas es "//"
     * 
     * @author Darío Aldana
     * 
     * @return usersGuide string actualizado
     */
    public String popUser(String usersGuide, String relsGuide){
        boolean breakWhile = false; 
        while (true) {
            try {
                String idStr = JOptionPane.showInputDialog(null, 
                        "Ingrese el ID del usuario a eliminar", 
                        "Eliminar Usuario", 
                        JOptionPane.QUESTION_MESSAGE);

                if (idStr == null){
                    return null;
                }
                
                int id = Integer.parseInt(idStr);
                
                for (int i = 0; i < usersArray.length; i++) {
                    if (id == usersArray[i]){
                        index = i; 
                        this.index = index;
                        this.idStr = idStr;
                        breakWhile = true;
                    }
                }
                
                if (breakWhile){
                    break;
                } else{
                    throw new Exception(); 
                }
 
            } catch(java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Ingrese un valor válido\n", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                
                }  catch (Exception e){
                    JOptionPane.showMessageDialog(null,
                        "Error:\nID de Usuario No Encontrado", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        
        String[] usersGuideArray = usersGuide.split("//");
        String[] usersGuideArrayFinal = new String[usersGuideArray.length -1];
        boolean popped = false; 
        int j = 0;
        
        JOptionPane.showMessageDialog(null, 
                "Se eliminará el usuario:\n" + usersGuideArray[index], 
                "Eliminar Usuario", 
                JOptionPane.INFORMATION_MESSAGE);
        
        for (int i = 0; i < usersGuideArray.length; i++) {
           if (i == index){
               popped = true; 
            } else {
                if (popped){
                    usersGuideArrayFinal[j-1] = usersGuideArray[i];
                } else {
                    usersGuideArrayFinal[j] = usersGuideArray[i];
                }
           }
            j++; 
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < usersGuideArrayFinal.length; i++) {
            sb.append(usersGuideArrayFinal[i]);
            sb.append("//");
        }
        
        usersGuide = sb.toString(); 
        
        
        this.changeMade = true; 
        return usersGuide;
    }
    
    /**
     * popUsersRels borra las relaciones hechas por un usuario
     * que va a ser eliminado, a modo de que en el grafo no 
     * queden aristas sueltos, pues no se puede tener una relación
     * con un usuario eliminado.
     *

     * @param idStr id del usuario que se desea eliminar
     * 
     * @param relsGuide mega string de la lista de relaciones
     *          cuyo separador entre líneas es "//"
     * 
     * @return relsGuide string de relaciones actualizado
     */
    public String popUsersRels(String idStr, String relsGuide){
        
        int counter = 0;
        String[] relsGuideArray = relsGuide.split("//");
        
        for (int i = 0; i < relsGuideArray.length; i++) {
            if (relsGuideArray[i].contains(idStr)){
                counter++; 
            }
        }
        int[] indexs = new int[counter];
        /**
         * int counter permite saber cuántas relaciones
         * tenía el usuario que vamos a eliminar, ese valor será
         * la longitud de un arreglo de enteros llamado indexs, que
         * almacenará los índices de las relaciones a eliminar en 
         * el arreglo de relaciones relsGuideArray
         */
        
        int k = 0; 
        for (int i = 0; i < relsGuideArray.length; i++) {
            if (relsGuideArray[i].contains(idStr)){
                indexs[k]=i; 
                k++;  
            } 
            if (k>counter){
                break;
            }
        }
        
        
        String[] relsGuideArrayFinal = new String[relsGuideArray.length-counter];
        
        /**
         * Aquí se procede a eliminar varios elementos de un array. 
         * Básicamente se hace un array de una longitud menor y se
         * pasan todos los elementos en el orden correcto pero saltando
         * al que se va a eliminar. Como pueden ser varios elementos, 
         * se hace un ciclo for y el proceso está en la siguiente función 
         */
        
        int deleted = 0;
        for (int i = 0; i < indexs.length; i++) {
            relsGuideArrayFinal = removeRel(indexs[i]-deleted, relsGuideArray);
            relsGuideArray = relsGuideArrayFinal;
            deleted++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < relsGuideArrayFinal.length; i++) {
            sb.append(relsGuideArrayFinal[i]);
            sb.append("//");
        }
        
        relsGuide = sb.toString();
        return relsGuide;
    }
    
    
    /**
     * removeRel maneja dos variables de iteración, i y j.
     * Se recorrerá el array copiando todos los elementos
     * hasta que el booleano "popped" indique que ya nos saltamos
     * el elemento que queríamos eliminar, de esa manera, ya
     * vamos agregando a los elementos en el array nuevo en una 
     * posición menor a la que están en el array original
     * 
     * @param index índice de la relación a eliminar en el array de
     *          relaciones
     * @param relsGuideArray array [strings] de relaciones 
     * 
     * @author Dario Aldana
     * 
     * @return relsGuideArrayFinal relsGuideArray actualizado
     */
    public String[] removeRel(int index, String[] relsGuideArray){
        
        boolean popped = false;
        String[] relsGuideArrayFinal = new String[relsGuideArray.length-1];
        int j= 0; 
        
        for (int i = 0; i < relsGuideArray.length; i++) {
           if (i == index){
               popped = true; 
            } else {
                if (popped){
                    relsGuideArrayFinal[j-1] = relsGuideArray[i];
                } else {
                    relsGuideArrayFinal[j] = relsGuideArray[i];
                }
           }
            j++; 
        }
        
        return relsGuideArrayFinal;
    }
    
    
    
    
    /** addRel permite añadir una relacion, recibe el mega string
     * de las relaciones y formula una nueva pidiendo ambos id
     * y el tiempo. Luego lo concatena con el string recibido
     * 
     * @param relsGuide mega string de la lista de relaciones
     *          cuyo separador entre líneas es "//"
     * 
     * @author Darío Aldana

     * @return relsGuide actualizado. 
     */
    public String addRel(String relsGuide){
        
        String[] relsGuideArray = relsGuide.split("//");
        boolean usersExists = false;
                
        while (true){
            try{
                String timeStr = JOptionPane.showInputDialog(null, 
                            "Ingrese el tiempo de la relación a añadir", 
                            "Añadir Relacion", 
                            JOptionPane.QUESTION_MESSAGE); 
                
                if (timeStr == null){
                    return null;
                }
                int time = Integer.parseInt(timeStr);

                String id1Str = JOptionPane.showInputDialog(null, 
                            "Ingrese el ID del primer usuario de la relación", 
                            "Añadir Relacion", 
                            JOptionPane.QUESTION_MESSAGE); 
                
                if (id1Str == null){
                    return null;
                }
                int id1 = Integer.parseInt(id1Str);

                String id2Str = JOptionPane.showInputDialog(null, 
                            "Ingrese el ID del segundo usuario de la relación", 
                            "Añadir Relacion", 
                            JOptionPane.QUESTION_MESSAGE); 

                if (id2Str == null){
                    return null;
                }
                
                int id2 = Integer.parseInt(id2Str); 
                
                for (int i = 0; i < size; i++) {
                    if (relsGuideArray[i].contains(id1Str) && relsGuideArray[i].contains(id2Str)){
                        throw new RuntimeException(); 
                    }
                }

                for (int i = 0; i < size; i++) {
                    if (usersArray[i] == id1){
                        for (int j = 0; j < size; j++) {  
                            if (usersArray[j]==id2){
                                usersExists = true; 

                            }
                        }
                    }
                }

                if (!usersExists){
                    throw new Exception();
                }
                
                this.timeStr = timeStr;
                this.id1Str = id1Str;
                this.id2Str = id2Str;
                break;
                
                
            } catch(java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Ingrese un valor válido\n", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                
            } catch (RuntimeException x){
                JOptionPane.showMessageDialog(null,
                    "Error:\nRelación ya Registrada", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,
                    "Error:\nUsuario(s) no Registrado(s)", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                }
        }
        
        
        String relToAdd = id1Str + ", " + id2Str + ", " + timeStr + "//"; 
        
        relsGuide = relsGuide.concat(relToAdd);
        
        
        this.changeMade = true; 
        return relsGuide; 
    }
    
    
    
    
    /**popRel borra una relación habiendo recibido ambos ID
     * 
     * @param relsGuide mega string de la lista de relaciones
     *          cuyo separador entre líneas es "//"
     * 
     * @author Darío Aldana

     * @return relsGuide actualizado. 
     */
    public String popRel(String relsGuide){
        String[] relsGuideArray = relsGuide.split("//");
        String[] relsGuideArrayFinal = new String[relsGuideArray.length-1];
        
        boolean relExists = false; 
        
        while (true){
            try{
                id1Str = JOptionPane.showInputDialog(null, 
                            "Ingrese el ID del primer usuario de la relación", 
                            "Eliminar Relacion", 
                            JOptionPane.QUESTION_MESSAGE); 
                
                if (id1Str == null){
                    return null;
                }
                int id1 = Integer.parseInt(id1Str);

                id2Str = JOptionPane.showInputDialog(null, 
                            "Ingrese el ID del segundo usuario de la relación", 
                            "Eliminar Relacion", 
                            JOptionPane.QUESTION_MESSAGE); 

                if (id2Str == null){
                    return null;
                }
                
                int id2 = Integer.parseInt(id2Str);
                
                for (int i = 0; i < relsGuideArray.length; i++) {
                    if (relsGuideArray[i].contains(id1Str) && relsGuideArray[i].contains(id2Str)){
                        this.index = i; 
                        relExists = true; 
                        break;
                    }
                }
                
                if (!relExists){
                    throw new RuntimeException(); 
                }
                
                this.id1Str = id1Str; 
                this.id2Str = id2Str;      
                break;

            } catch(java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Ingrese un valor válido\n", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                
            } catch (RuntimeException r){
                JOptionPane.showMessageDialog(null,
                        "Error:\nLa relación entre " + id1Str + " y " + id2Str + "no existe.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null,
                        "Error", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        
        boolean popped = false; 
        int j = 0; 
        
        /**
         * El siguiente algoritmo permite borrar la relación 
         * creando otro array de menor longitud y copiando 
         * todos los elementos de uno en otro menos el que se 
         * desea eliminar. 
         */
        
        for (int i = 0; i < relsGuideArray.length; i++) {
           if (i == index){
               popped = true; 
            } else {
                if (popped){
                    relsGuideArrayFinal[j-1] = relsGuideArray[i];
                } else {
                    relsGuideArrayFinal[j] = relsGuideArray[i];
                }
           }
            j++; 
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < relsGuideArrayFinal.length; i++) {
            sb.append(relsGuideArrayFinal[i]);
            sb.append("//");
        }
        
        relsGuide = sb.toString(); 
        System.out.println(relsGuide);
        
        this.changeMade = true; 
        return relsGuide;
    }
  
    
    /**rewriteDatabase es el fin de toda la clase Modify
     * sea el camino que se escoja, si se realizó algún cambio
     * esta función lo pasará a la base de datos. 
     * 
     * @param usersGuide mega string de la lista de usuarios
     *          cuyo separador entre líneas es "//"
     * 
     * @param relsGuide mega string de la lista de relaciones
     *          cuyo separador entre líneas es "//"
     * 
     * @param path ruta del txt
     * 
     * @author Dario Aldana
     */
    
    
    public void rewriteDatabase(String path, String usersGuide, String relsGuide){
        
        String[] usersGuideArray = usersGuide.split("//");
        String[] relsGuideArray = relsGuide.split("//");
        

        try {
            PrintWriter pw = new PrintWriter(path);
            pw.println("Usuarios");
            for (int i = 0; i < usersGuideArray.length; i++) {
                pw.println(usersGuideArray[i]);
            }
            pw.println("Relaciones");
            for (int i = 0; i < relsGuideArray.length; i++) {
                pw.println(relsGuideArray[i]);
            }

            JOptionPane.showMessageDialog(null, 
                    "Base de datos Actualizada con Éxito"); 
            JOptionPane.showMessageDialog(null, 
                "Por favor, vuelva a cargar el archivo para ejecutar los cambios correspondientes",
                "Base de Datos Actualizada con Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            pw.close();
                    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos");
        }
        
        

    }
}
    
 
