/**
 * Implementation of the Simplex Method 
 * @version 0.9.1
 * @author José Alfredo Brambila Hernández <alfredo.brambila@outlook.com>
 * 
 * SOFTWARE LICENCE
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
 * and associated documentation files (the "Software"), to deal in the Software without restriction, 
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */

package domain;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class FileLoader {
    private int nvars;
    private int nrest;
    private boolean isMax;
    private double[] z;
    private double[][] array = null;
    //int[] igualdades; // 1 > | 2 >= | 3 < | 4 <= | 5 = |
    private String[] igualdades; // 1 > | 2 >= | 3 < | 4 <= | 5 = |
    
    /*public static void main(String[] args) {
        FileLoader a = new FileLoader();
        a.leerArchivo();
        
    }*/
    
    public void leerArchivo(String ruta) {
        try {
            Scanner input = new Scanner(new File(ruta));
            int i=0;
            String linea;
            StringTokenizer tokens;
            String valor;
            String signo;
            int f=0;
            int c=0;
            while (input.hasNextLine()) {
                linea = input.nextLine();
                if(i == 2 && getArray() == null) {
                    setArray(new double[this.getNrest()][this.getNvars() + 1]);
                }
                switch(i){
                    case 0:
                        tokens=new StringTokenizer(linea,":");
                        tokens.nextToken();
                        this.setNvars(Integer.parseInt(tokens.nextToken().toString().trim()));
                        break;
                    case 1:
                        tokens=new StringTokenizer(linea,":");
                        tokens.nextToken();
                        this.setNrest(Integer.parseInt(tokens.nextToken().toString().trim()));
                        break;
                    case 2:
                        tokens=new StringTokenizer(linea,":");
                        tokens.nextToken();
                        String objetivo = tokens.nextToken().toString().trim();
                        if(objetivo.equals("max") || objetivo.equals("MAX")) {
                            this.setIsMax(true);
                        } else {
                            this.setIsMax(false);
                        }
                        break;
                    case 3://z
                        c = 0;
                        setZ(new double[this.getNvars()]);
                        tokens=new StringTokenizer(linea,":");
                        tokens.nextToken();
                        linea = tokens.nextToken();
                        tokens=new StringTokenizer(linea," ");
                        signo="";
                        c=0;
                        while(tokens.hasMoreTokens()) {
                            valor = tokens.nextToken().toString();
                            if(isNumeric(valor)) {
                                if(signo.isEmpty()) {
                                    this.getZ()[c++] = Double.parseDouble(valor);
                                } else if(signo.equals("+")){
                                    this.getZ()[c++] = Double.parseDouble(valor);
                                    signo = "";
                                } else {
                                    this.getZ()[c++] = Double.parseDouble("-"+valor);
                                    signo = "";
                                }
                            } else {
                                signo = valor;
                            }
                            
                        }
                        break;
                    case 4:
                        this.setIgualdades(new String[this.getNrest()]);
                        break;
                    default:
                        //tokens=new StringTokenizer(linea,":");
                        //tokens.nextToken();
                        //linea = tokens.nextToken();
                        tokens=new StringTokenizer(linea," ");
                        signo="";
                        c=0;
                        while(tokens.hasMoreTokens()) {
                            valor = tokens.nextToken().toString();
                            if(isNumeric(valor)) {
                                if(signo.isEmpty()) {
                                    this.getArray()[f][c++] = Double.parseDouble(valor);
                                } else if(signo.equals("+")){
                                    this.getArray()[f][c++] = Double.parseDouble(valor);
                                    signo = "";
                                } else if(signo.equals("-")) {
                                    this.getArray()[f][c++] = Double.parseDouble("-"+valor);
                                    signo = "";
                                } else if(signo.equals(">")) {
                                    //int[] igualdades; // 1 > | 2 >= | 3 < | 4 <= | 5 = |
                                    this.getIgualdades()[f] = ">";
                                    this.getArray()[f][c++] = Double.parseDouble(valor);
                                    signo = "";
                                } else if(signo.equals(">=")) {
                                    this.getIgualdades()[f] = ">=";
                                    this.getArray()[f][c++] = Double.parseDouble(valor);
                                    signo = "";
                                } else if(signo.equals("<")) {
                                    this.getIgualdades()[f] = "<";
                                    this.getArray()[f][c++] = Double.parseDouble(valor);
                                    signo = "";
                                } else if(signo.equals("<=")) {
                                    this.getIgualdades()[f] = "<=";
                                    this.getArray()[f][c++] = Double.parseDouble(valor);
                                    signo = "";
                                } else if(signo.equals("=")) {
                                    this.getIgualdades()[f] = "=";
                                    this.getArray()[f][c++] = Double.parseDouble(valor);
                                    signo = "";
                                } 
                            } else {
                                signo = valor;
                            }
                            
                        }
                        f++;
                        //this.objetivo = tokens.nextToken().toString().trim();
                        break;
                }
                i++;
                System.out.println(linea);
            }
            input.close();
            System.out.println(this.getNvars() + " " + this.getNrest() + " " + this.isIsMax());
            
            for(int ii=0; ii<getArray().length; ii++) {
                for(int jj=0; jj<getArray()[0].length; jj++) {
                    System.out.print(" [" + getArray()[ii][jj] + "] ");
                }
                System.out.println("");
            }
            System.out.println(Arrays.toString(this.getIgualdades()));
            System.out.println(Arrays.toString(getZ()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean isNumeric(String str) {
		return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * @return the nvars
     */
    public int getNvars() {
        return nvars;
    }

    /**
     * @param nvars the nvars to set
     */
    public void setNvars(int nvars) {
        this.nvars = nvars;
    }

    /**
     * @return the nrest
     */
    public int getNrest() {
        return nrest;
    }

    /**
     * @param nrest the nrest to set
     */
    public void setNrest(int nrest) {
        this.nrest = nrest;
    }

    /**
     * @return the isMax
     */
    public boolean isIsMax() {
        return isMax;
    }

    /**
     * @param isMax the isMax to set
     */
    public void setIsMax(boolean isMax) {
        this.isMax = isMax;
    }

    /**
     * @return the z
     */
    public double[] getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(double[] z) {
        this.z = z;
    }

    /**
     * @return the array
     */
    public double[][] getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(double[][] array) {
        this.array = array;
    }

    /**
     * @return the igualdades
     */
    public String[] getIgualdades() {
        return igualdades;
    }

    /**
     * @param igualdades the igualdades to set
     */
    public void setIgualdades(String[] igualdades) {
        this.igualdades = igualdades;
    }
    
}
