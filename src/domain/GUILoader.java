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

import java.util.Arrays;

/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class GUILoader {
    private int nvars;
    private int nrest;
    private boolean isMax;
    private double[] z;
    private double[][] array = null;
    private String[] igualdades;
    
    public void leerDeGUI() {
        System.out.println(this.getNvars() + " " + this.getNrest() + " " + this.isIsMax());

        for (int ii = 0; ii < getArray().length; ii++) {
            for (int jj = 0; jj < getArray()[0].length; jj++) {
                System.out.print(" [" + getArray()[ii][jj] + "] ");
            }
            System.out.println("");
        }
        System.out.println(Arrays.toString(this.getIgualdades()));
        System.out.println(Arrays.toString(getZ()));
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
