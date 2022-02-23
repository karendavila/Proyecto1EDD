/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import usuario.Usuario;


public class Grafo {
    private Integer[][] matrix;
    private Lista<Usuario> usuarios;

    public Grafo(int size) {
        matrix = new Integer[size][size];
        usuarios = new Lista<>();
    }
    
    public void createGrafo(String usuariosStr, String aristas){
          String[] lineUsers = usuariosStr.split("//");
          String[] lineEdge = usuariosStr.split("//");
          for (int i = 0; i < lineUsers.length; i++) {
              String[] datos = lineUsers[i].split(",");
              Usuario usuario = new Usuario(Integer.parseInt(datos[0].trim()), datos[1].trim());              
              this.usuarios.insertar(usuario);              
          }
          
          for (int i = 0; i < lineEdge.length; i++) {
            String[] datos= lineEdge[i].split(",");
            Usuario userX = new Usuario(Integer.parseInt(datos[0].trim()),"");
            Usuario userY = new Usuario(Integer.parseInt(datos[1].trim()),"");
            int posX = this.usuarios.buscarPosicion(userX);
            int posY = this.usuarios.buscarPosicion(userY);
            this.matrix[posX][posY]= Integer.parseInt(datos[2].trim());
        }
    }
    
    
}
