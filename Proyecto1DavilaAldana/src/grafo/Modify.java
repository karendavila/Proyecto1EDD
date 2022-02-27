/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
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
     
    
    
    
    public Modify(int[] usersArray, String usersGuide, String relsGuide, String path){
        this.changeMade = false; 
        this.usersArray = usersArray;
        this.size = usersArray.length;
       
        
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
        System.out.println(userToAdd);
        System.out.println(usersGuide);
        
        this.changeMade = true; 
        return usersGuide;
       }
    
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
    
    public String popUsersRels(String idStr, String relsGuide){
        
        int counter = 0;
        String[] relsGuideArray = relsGuide.split("//");
        
        for (int i = 0; i < relsGuideArray.length; i++) {
            if (relsGuideArray[i].contains(idStr)){
                counter++; 
            }
        }
        int[] indexs = new int[counter];
        
        
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
    
    public String[] removeRel(int index, String[] relsGuideArray){
        System.out.println("INDICE  "+ index);
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
        
        System.out.println(relsGuide);
        String relToAdd = id1Str + ", " + id2Str + ", " + timeStr + "//"; 
        System.out.println(relToAdd);
        relsGuide = relsGuide.concat(relToAdd);
        System.out.println(relsGuide);
        
        this.changeMade = true; 
        return relsGuide; 
    }
    
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
        
        
        System.out.println(id1Str);
        System.out.println(id2Str);
        System.out.println(index);
        
        boolean popped = false; 
        int j = 0; 
        
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
    
 
