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

import javax.swing.JOptionPane;

/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class SimplexTable {
    private FileLoader fileLoader;
    private GUILoader guiLoader;
    private double[][] simplexTable = null;
    int nvars;
    int nrest;
    private boolean isMax;
    private double[] z;
    private double[][] array = null;
    private String[] igualdades;
    
    int entrada;
    int salida;
    double valorPivote;
    //int vb[] = null;
    private String valoresResultado;
    
    boolean dosFases = false;
    boolean solucion = false;
    
    double sVector[];
    double rVector[];
    double cVector[];
    
    int cS =0; int cR=0;
    boolean ban=false;
    double utimoZ=0;
    boolean uz = false;
    
    public SimplexTable() {
        //simplexFromFile();
        
    }
    
    public void simplexFromFile(String file) {
        System.out.println("Cargando desde Archivo \n\n");
        //"C:\\Users\\al_x3\\Documents\\Maestria\\ProgramacionMatematica\\instancia.ins"
        getValuesFromFile(file);
        //getValuesFromFile("C:\\Users\\al_x3\\Documents\\Maestria\\ProgramacionMatematica\\instancia02.ins");
        construyeSimplex();
        
        
        /*while(!solucion) {
            genPivote();
            solucion = iteraSimplex();
        }
        
        if(solucion) {
            buscaResultado();
        }*/
    }
    
    public void resolver() {
        while(!solucion) {
            genPivote();
            solucion = iteraSimplex();
        }
        
        if(solucion) {
            buscaResultado();
        }
    }
    
    public boolean pasoAPaso() {
        if(!solucion) {
            genPivote();
            solucion = iteraSimplex();
        }
        
        if(solucion) {
            buscaResultado();
        }
        
        return solucion;
    }
    
        
    public void simplexFromGUI(GUILoader gLoader) {
        System.out.println("Cargando desde GUI \n\n");
        getValuesFromGUI(gLoader);
        construyeSimplex();
        /*boolean solucion = false;
        
        while(!solucion) {
            genPivote();
            solucion = iteraSimplex();
        }
        
        if(solucion) {
            buscaResultado();
        }*/
    }
    
    private void getValuesFromGUI(GUILoader gLoader) {
        guiLoader = gLoader;
        this.nvars = guiLoader.getNvars();
        this.nrest = guiLoader.getNrest();
        this.isMax = guiLoader.isIsMax();
        this.z = guiLoader.getZ();
        this.array = guiLoader.getArray();
        this.igualdades = guiLoader.getIgualdades();
        
        this.sVector = new double[this.nrest];
        this.rVector = new double[this.nrest];
        this.cVector = new double[this.nrest];
        this.vectorEnCeros(cVector);
        this.vectorEnCeros(sVector);
        this.vectorEnCeros(rVector);
        
        int cvadd = 0;
        String cvaddstr="";
        int cvs = 0; int cvr = 0;
        for(int i=0; i<this.igualdades.length; i++) {
            switch(this.igualdades[i]) {
                case "<=":
                    cvadd = cvadd + 1;
                    cvaddstr += "S"+(++cvs)+" ";
                    this.sVector[i] = 1.0;
                    break;
                case ">=":
                    cvadd = cvadd + 2;
                    cvaddstr += "-S"+(++cvs)+" R"+(++cvr)+" ";
                    this.dosFases = true;
                    this.sVector[i] = -1.0;
                    this.rVector[i] = 1.0;
                    this.cVector[i] = -1.0;
                    break;
                case "=":
                    cvadd = cvadd + 1;
                    cvaddstr += "R"+(++cvr)+" ";
                    this.dosFases = true;
                    this.rVector[i] = 1.0;
                    this.cVector[i] = -1.0;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en desigualdad de restriccion", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        this.simplexTable = new double[this.nrest+1][this.nvars+cvadd+2];
        inicializaEnCerosMatriz(this.simplexTable);
        
        System.out.println(cvaddstr);
        System.out.println(cvadd);
    }
    
    public void getValuesFromFile(String ruta) {
        fileLoader = new FileLoader();
        fileLoader.leerArchivo(ruta);
        this.nvars = fileLoader.getNvars();
        this.nrest = fileLoader.getNrest();
        this.isMax = fileLoader.isIsMax();
        this.z = fileLoader.getZ();
        this.array = fileLoader.getArray();
        this.igualdades = fileLoader.getIgualdades();
        
        this.sVector = new double[this.nrest];
        this.rVector = new double[this.nrest];
        this.cVector = new double[this.nrest];
        
        this.vectorEnCeros(cVector);        
        this.vectorEnCeros(sVector);
        this.vectorEnCeros(rVector);
        
        int cvadd = 0;
        String cvaddstr="";
        int cvs = 0; int cvr = 0;
        for(int i=0; i<this.igualdades.length; i++) {
            switch(this.igualdades[i]) {
                case "<=":
                    cvadd = cvadd + 1;
                    cvaddstr += "S"+(++cvs)+" ";
                    this.sVector[i] = 1.0;
                    break;
                case ">=":
                    cvadd = cvadd + 2;
                    cvaddstr += "-S"+(++cvs)+" R"+(++cvr)+" ";
                    this.dosFases = true;
                    this.sVector[i] = -1.0;
                    this.rVector[i] = 1.0;
                    this.cVector[i] = -1.0;
                    break;
                case "=":
                    cvadd = cvadd + 1;
                    cvaddstr += "R"+(++cvr)+" ";
                    this.dosFases = true;
                    this.rVector[i] = 1.0;
                    this.cVector[i] = -1.0;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en desigualdad de restriccion", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        this.simplexTable = new double[this.nrest+1][this.nvars+cvadd+2];
        inicializaEnCerosMatriz(this.simplexTable);
        
        System.out.println(cvaddstr);
        System.out.println(cvadd);
        
        
    }
    
    private void buscaResultado() {
        if(!this.isMax) this.simplexTable[this.simplexTable.length-1][1] = this.simplexTable[this.simplexTable.length-1][1] * -1;
        this.valoresResultado = "<html>";
        for(int i=0; i<this.simplexTable.length-1; i++) {
            if(this.simplexTable[i][0] >= 1 && this.simplexTable[i][0] <= this.nvars) {
                System.out.println("X"+(int)this.simplexTable[i][0] + " = " + this.simplexTable[i][1]);
                this.valoresResultado += "X"+(int)this.simplexTable[i][0] + " = <b>" + this.simplexTable[i][1]+"</b><br>";
            }
        }
        System.out.println("Z = " + this.simplexTable[this.simplexTable.length-1][1]);
        this.valoresResultado += "<br>";
        this.valoresResultado += "Z = <b>" + this.simplexTable[this.simplexTable.length-1][1]+"</b></html>";
        
    }
    
    private void imprimeTablaSimplex() {
        System.out.println("*** Tabla simplex ***");
        for (int i = 0; i < this.simplexTable.length; i++) {
            for (int j = 0; j < this.simplexTable[0].length; j++) {
                System.out.print(this.simplexTable[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    private void genPivote() {
        // i-nvars+1;
        this.entrada = 0;
        this.salida = 0;
        if(this.isMax) {
            System.out.println("PIV MAX");
            double aux;
            double aux2;
            
            aux = this.simplexTable[this.simplexTable.length-1][2];
            this.entrada = 2;
            for(int i=3; i<this.simplexTable[0].length; i++) {
                if(this.simplexTable[this.simplexTable.length-1][i] < aux) {
                    aux = this.simplexTable[this.simplexTable.length-1][i];
                    this.entrada = i;
                }
            }
            
            aux = -1;
            aux2 = -1;
            System.out.println("Pivoteo");
            for(int i=0; i<this.simplexTable.length-1; i++) {
                if(this.simplexTable[i][1]/this.simplexTable[i][this.entrada] >= 0) {
                    aux = this.simplexTable[i][1]/this.simplexTable[i][this.entrada];                    
                }
                if(aux2 == -1) {
                    aux2 = aux;
                    this.salida = i;
                }
                if(aux < aux2 && aux >= 0) {
                    aux2 = aux;
                    this.salida = i;
                }
            }
            //this.simplexTable[this.entrada-1][0] = this.entrada-1;
            this.simplexTable[this.salida][0] = this.entrada-1;
            this.valorPivote = this.simplexTable[this.salida][this.entrada];
            this.imprimeTablaSimplex();
            System.out.println("Entrada: " + this.entrada + "\nSalida: " + this.salida);
            //System.out.println("Entrada: " + this.entrada + "\nSalida: " + this.salida);
            System.out.println("Valor pivote: " + this.valorPivote);
        } else {
            
            System.out.println("PIV MIN");
            double aux;
            double aux2;
            
            aux = this.simplexTable[this.simplexTable.length-1][2];
            this.entrada = 2;
            for(int i=3; i<this.simplexTable[0].length; i++) {
                if(this.simplexTable[this.simplexTable.length-1][i] < aux) {
                    aux = this.simplexTable[this.simplexTable.length-1][i];
                    this.entrada = i;
                }
            }
            
            aux = -1;
            aux2 = -1;
            System.out.println("Pivoteo");
            for(int i=0; i<this.simplexTable.length-1; i++) {
                if(this.simplexTable[i][1]/this.simplexTable[i][this.entrada] >= 0) {
                    aux = this.simplexTable[i][1]/this.simplexTable[i][this.entrada];                    
                }
                if(aux2 == -1) {
                    aux2 = aux;
                    this.salida = i;
                }
                if(aux < aux2 && aux >= 0) {
                    aux2 = aux;
                    this.salida = i;
                }
            }
            //this.simplexTable[this.entrada-1][0] = this.entrada-1;
            this.simplexTable[this.salida][0] = this.entrada-1;
            this.valorPivote = this.simplexTable[this.salida][this.entrada];
            this.imprimeTablaSimplex();
            System.out.println("Entrada: " + this.entrada + "\nSalida: " + this.salida);
            System.out.println("Valor pivote: " + this.valorPivote);
        }
    }
    
    private int cuentaElementos1Vector(double[] vector) {
        int n=0;
        for(double v : vector) {
            if(v != 0.0) {
                n++;
            }
        }
        return n;
    }
    
    private void vectorEnCeros(double[] vector) {
        for(int i=0; i<vector.length; i++) {
            vector[i] = 0.0;
        }
    }
    
    public void construyeSimplex() {
        if(!this.dosFases) {
            for(int i=0; i<this.array.length; i++) {
                //this.simplexTable[i][0] = i+1;
                this.simplexTable[i][1] = array[i][array[0].length-1];
                this.simplexTable[i][this.array[0].length+i+1] = 1.0;
                for(int j=0; j<this.array[0].length-1; j++) {
                    this.simplexTable[i][j+2] = array[i][j];
                }
            }
            
            if(this.isMax) {
                for(int i=0; i<this.z.length; i++) {
                    this.simplexTable[this.simplexTable.length-1][i+2] = this.z[i] * -1;
                }
            } else {
                for(int i=0; i<this.z.length; i++) {
                    this.simplexTable[this.simplexTable.length-1][i+2] = this.z[i] * -1;
                }
            }
            
            
            this.imprimeTablaSimplex();
          
        } else {
            // Dos Fases...
            //JOptionPane.showMessageDialog(null, "Es dos fases", "Info", JOptionPane.INFORMATION_MESSAGE);
            int nS = this.cuentaElementos1Vector(this.sVector);
            int nR = this.cuentaElementos1Vector(this.rVector);
            cS =0; cR=0;
            for(int i=0; i<this.array.length; i++) {
                //this.simplexTable[i][0] = i+1;
                this.simplexTable[i][1] = array[i][array[0].length-1]; // d
                //tam+cs+1	tam+n2+cr+1
                if(this.sVector[i] != 0) {
                    this.simplexTable[i][this.array[0].length+cS+1] = this.sVector[i];
                    cS++;
                }
                if(this.rVector[i] != 0) {
                    this.simplexTable[i][this.array[0].length+cR+nS+1] = this.rVector[i];
                    cR++;
                }
                for(int j=0; j<this.array[0].length-1; j++) {
                    this.simplexTable[i][j+2] = array[i][j];
                }
            }
            
            for(int i=0; i<this.cVector.length; i++) {
                System.out.println("C[i] " + this.cVector[i]);
                if(this.cVector[i]==-1.0) {
                    for(int j=0; j<this.z.length+nR; j++) {
                        System.out.println("*** " + this.simplexTable[i][j+1] + " += " +(this.simplexTable[i][j+1]*-1.0));
                        this.simplexTable[this.simplexTable.length-1][j+1] += this.simplexTable[i][j+1]*-1.0;
                    }
                }
            }
        }
    }
    
    public boolean iteraSimplex() {
        boolean iteracionFinal = false;
        //dividir renglon entre pivote
        iteracionFinal = true;
        for(int i=1; i<this.simplexTable[0].length; i++) {
            if(Math.round(this.simplexTable[this.simplexTable.length-1][i])<0 && !this.uz) {
                iteracionFinal = false;
            } 
        }
        if(iteracionFinal) {
            return true;
        }
       
        
        for(int i=1; i<this.simplexTable[0].length; i++) {
            this.simplexTable[this.salida][i] = this.simplexTable[this.salida][i]/this.valorPivote;
        }
        
        double val = 0;
        //System.out.println("--- OP --");
        for(int i=0; i<this.simplexTable.length; i++) {
            val = simplexTable[i][this.entrada];
            //val = simplexTable[i][this.salida];
            //System.out.println("Val: " + val);
            //System.out.println("val: " + val);
            for(int j=1; j<this.simplexTable[0].length; j++) {
                if(i!=this.salida) {
                    this.simplexTable[i][j] = this.simplexTable[i][j] - (this.simplexTable[this.salida][j]*val);
                    this.simplexTable[i][j] = (Math.floor(this.simplexTable[i][j] * 100) / 100d);
                }
            }
        }
        
        this.imprimeTablaSimplex();
        System.out.println("--- FIN OP ---");
        //hacer ceros 
        //ban = false;
        iteracionFinal = true;
        for(int i=1; i<this.simplexTable[0].length; i++) {
            if(Math.round(this.simplexTable[this.simplexTable.length-1][i])<0) {
                iteracionFinal = false;
            } 
        }
        // this.utimoZ = this.simplexTable[this.simplexTable.length-1][1];
        if(this.utimoZ == this.simplexTable[this.simplexTable.length-1][1]) {
            iteracionFinal = true;
        } else {
            this.utimoZ = this.simplexTable[this.simplexTable.length-1][1];
        }
        
        System.out.println("Iteracion final " + iteracionFinal);
        System.out.println("Ban " + ban);
        System.out.println("Uz " + this.uz);
        System.out.println("Uz " + this.utimoZ);
        
        if (iteracionFinal) {
            if (this.dosFases && ban == false) {
                //MAXIMIZAR
                if (this.isMax) {
                    System.out.println("FINAL");
                    double[] cj = new double[this.nvars + cS];
                    int ind = 1;
                    cj[0] = 0;
                    for (int i = 0; i < z.length; i++) {
                        cj[ind++] = z[i];
                    }
                    for (int i = ind; i < this.nvars + cS; i++) {
                        cj[i] = 0;
                    }
                    int iz=0;
                    for(int i=0; i<this.cVector.length; i++) {
                        if(this.cVector[i]!=0) {
                            this.cVector[i] = this.z[iz++];
                        }
                    }
                    
                    for(int i=0; i<this.cVector.length; i++) {
                        for(int j=1; j<this.simplexTable[0].length-cR-1; j++) {
                            this.simplexTable[this.simplexTable.length-1][j] += (this.simplexTable[i][j]*this.cVector[i]);
                        }
                    }
                    for(int j=1; j<this.simplexTable[0].length-cR-1; j++) {
                        this.simplexTable[this.simplexTable.length-1][j] = this.simplexTable[this.simplexTable.length-1][j]-cj[j-1];
                    }
                    
                    ban = true;
                    iteracionFinal = false;
                    
                } else {
                    //MINIMIZAR
                    System.out.println("FIN MIN");
                    double[] cj = new double[this.nvars + cS];
                    int ind = 1;
                    cj[0] = 0;
                    for (int i = 0; i < z.length; i++) {
                        cj[ind++] = z[i]*-1;
                    }
                    for (int i = ind; i < this.nvars + cS; i++) {
                        cj[i] = 0;
                    }
                    int iz=0;
                    for(int i=0; i<this.cVector.length; i++) {
                        if(this.cVector[i]!=0) {
                            this.cVector[i] = this.z[iz++]*-1;
                        }
                    }
                    
                    for(int i=0; i<this.cVector.length; i++) {
                        for(int j=1; j<this.simplexTable[0].length-cR-1; j++) {
                            this.simplexTable[this.simplexTable.length-1][j] += (this.simplexTable[i][j]*this.cVector[i]);
                        }
                    }
                    for(int j=1; j<this.simplexTable[0].length-cR-1; j++) {
                        this.simplexTable[this.simplexTable.length-1][j] = this.simplexTable[this.simplexTable.length-1][j]-cj[j-1];
                    }
                    
                    ban = true;
                    iteracionFinal = false;
                    
                    /*System.out.println(this.utimoZ + "=" + this.simplexTable[this.simplexTable.length-1][1]);
                    if(this.utimoZ == this.simplexTable[this.simplexTable.length-1][1]) {
                        iteracionFinal = true;
                        this.simplexTable[this.simplexTable.length-1][1] = this.simplexTable[this.simplexTable.length-1][1] * -1;
                        this.uz = true;
                    } else {
                        this.utimoZ = this.simplexTable[this.simplexTable.length-1][1];
                    }*/
                    
                }
            }
        }
        if(iteracionFinal) {
            System.out.println("Tabla final:");
        }
        this.imprimeTablaSimplex();
        return iteracionFinal;
    }
    
    private void inicializaEnCerosMatriz(double[][] matrix) {
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                    matrix[i][j] = 0.0;
            }
        }
    }
    
    public double[][] getSimplexTableMatrix() {
        return this.simplexTable;
    }
    
    public String getValoresResultado() {
        return valoresResultado;
    }
    
    // <= +S        coef z 0
    // >= +R -S     coef z +M(min) -M(max)
    //  = +R        coef z +M(min) -M(max)
    
    public static void main(String[] args) {
        SimplexTable a = new SimplexTable();
        //a.leerArchivo();
    }
}
