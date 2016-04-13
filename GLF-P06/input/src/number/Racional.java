package number;

import vector.VectorOps;

/**
 * Clase que modela los numeros racionales.
 * 
 * @author garciparedes
 *
 */
public class Racional implements VectorOps<Racional> {
	
	
	/**
	 * ******************************** Atributos de la clase ********************************
	 */
	
	
	private static final int UNIDAD = 1;
	
	
	/**
	 * ******************************** Atributos del Objeto ********************************
	 */
	
	
	private int numerador;
	private int denominador;
	
	
	/**
	 * ******************************** Constructores ********************************
	 */
	
	
	/**
	 * Constructor de <code>Racional</code>.
	 * 
	 * Inicialiciza a 0 el valor del numero.
	 */
	public Racional(){
		this.numerador = 0;
		this.denominador = UNIDAD;
	}
	
	
	/**
	 * Constructor de <code>Racional</code>.
	 * 
	 * Inicializa la instancia con el valor proporcionado
	 * por numerador.
	 * 
	 * @param value int con el valor de inicializacion.
	 */
	public Racional(int value){
		this.numerador = value;
		this.denominador = UNIDAD;
	}
	
	
	/**
	 * Constructor de <code>Racional</code>.
	 * 
	 * Inicializa la instancia con el valor proporcionado
	 * por numerador.
	 * 
	 * 
	 * Este codigo para convertir un double a racional
	 * a sido obtenido de la web StackOverFlow 
	 * <a href="http://stackoverflow.com/a/14014200">http://stackoverflow.com/a/14014200</a>
	 * 
	 * @param value int con el valor de inicializacion.
	 */
	public Racional(double value) {
        String s = String.valueOf(value);
        int digitsDec = s.length() - 1 - s.indexOf('.');        

        int denom = 1;
        for(int i = 0; i < digitsDec; i++){
           value *= 10;
           denom *= 10;
        }
        int num = (int) Math.round(value);

        this.numerador = num;
        this.denominador = denom;
   }
	
	
	/**
	 * Constructor de <code>Racional</code>.
	 * 
	 * Inicializa la instancia con el valor proporcionado
	 * por los parametros <code>numerador</code> y <code>denominador</code>.
	 * 
	 * Utiliza la funcion <code>reducir</code> 
	 * para almacenar siempre el numero en su forma reducida.
	 * 
	 * @param value int con el valor de inicializacion.
	 */
	public Racional(int numerador, int denominador){
		assert(denominador != 0);

		this.numerador = numerador;	
		this.denominador = denominador;
		
		reducir();
	}
	
	/**
	 * ******************************** Getters y Setters ********************************
	 */
	
	/**
	 * Setter del numerador.
	 * 
	 * Utiliza la funcion <code>reducir</code> 
	 * para almacenar siempre el numero en su forma reducida.
	 * 
	 * @param value int con el nuevo valor del numerador.
	 */
	public void setNumerador(int value){
		this.numerador = value;
	
		reducir();
	}
	
	
	/**
	 * Setter del denominador.
	 * 
	 * Utiliza la funcion <code>reducir</code> 
	 * para almacenar siempre el numero en su forma reducida.
	 * 
	 * @param value int con el nuevo valor del denominador.
	 */
	public void setDenominador(int value){
		assert(value != 0);
		
		this.denominador = value;
		
		reducir();
	}
	
	
	/**
	 * Getter del numerador.
	 * 
	 * @return numerador int con el valor del numerador.
	 */
	public int getNumerador(){
		return numerador;
	}
	
	
	/**
	 * Getter del denominador.
	 * 
	 * @return denominador int con el valor del denominador.
	 */
	public int getDenominador(){
		return denominador;
	}
	
	
	/**
	 * Funcion que comprueba si el objeto invocante es entero.
	 *  
	 * @return isEntero boolean Valor booleano sobre si es o no entero.
	 */
	public boolean esEntero(){
		return getDenominador() == 1;
	}
	
	
	/**
	 * ******************************** Metodos Publicos ********************************
	 */
	
	
	/**
	 * Procedimiento que se encarga de reducir
	 * los valores de los atributos de Racional
	 * por otros que representan el mismo valor.
	 */
	public void reducir(){
		// valores absolutos:
		int absNum = Math.abs(getNumerador());
		int absDen = Math.abs(getDenominador());
		
		// signo del denominador:
		int signo = getDenominador()/absDen;
		int s = mcd(absNum,absDen);
		
		numerador = signo*(getNumerador()/s);
		denominador = signo*(getDenominador()/s);
	}
	
	
	/**
	 * Función encargada de sumar el objeto Racional
	 * invocante junto con el parametro <code>sumando</code>.
	 * 
	 * @param sumando Racional. Segundo sumando de la operacion.
	 * @return result Racional. Resultado de la operacion.
	 */
	@Override
	public Racional suma(Racional sumando){
		int numerador = getNumerador() * sumando.getDenominador()
				+ getDenominador() * sumando.getNumerador();
		
		int denominador = getDenominador() * sumando.getDenominador();
		
		return new Racional(numerador, denominador);
	}
	
	
	/**
	 * Función encargada de restar el objeto Racional
	 * invocante junto con el parametro <code>value</code>.
	 * 
	 * @param value Racional. Valor a restar de la operacion.
	 * @return result Racional. Resultado de la operacion.
	 */
	@Override
	public Racional resta(Racional value) {
		
		int numerador = getNumerador() * value.getDenominador()
				- getDenominador() * value.getNumerador();
		
		int denominador = getDenominador() * value.getDenominador();
		
		return new Racional(numerador, denominador);
	}
	
	/**
	 * Función encargada de multiplicar el objeto Racional
	 * invocante junto con el parametro <code>multiplicando</code>.
	 * 
	 * @param multiplicando Racional. Segundo multiplicando de la operacion.
	 * @return result Racional. Resultado de la operacion.
	 */
	public Racional multiplicacion (Racional multiplicando){
		return new Racional(getNumerador() * multiplicando.getNumerador(),
				getDenominador() * multiplicando.getDenominador());
	}
	
	

	/**
	 * Función encargada de multiplicar el objeto Racional
	 * invocante por el parametro <code>value</code>.
	 * 
	 * @param value Racional. Valor por el que multiplicar.
	 * @return result Racional. Resultado de la operacion.
	 */
	@Override
	public Racional multiplicacion(double value) {
		return multiplicacion(new Racional(value));
	}


	
	/**
	 * Función encargada de dividir el objeto Racional
	 * invocante junto con el parametro <code>divisor</code>.
	 * 
	 * @param divisor Racional. Divisor de la operacion.
	 * @return result Racional. Resultado de la operacion.
	 */
	public Racional division (Racional divisor){
		return new Racional(getNumerador() * divisor.getDenominador(),
				getDenominador() * divisor.getNumerador());
	}
	
	/**
	 * Función encargada de dividir el objeto Racional
	 * invocante entre  el parametro <code>value</code>.
	 * 
	 * @param value Double. valor entre el que dividir.
	 * @return result Racional. Resultado de la operacion.
	 */
	@Override
	public Racional division(double value) {
		return division(new Racional(value));
	}
	
	
	/**
     * Metodo que sobreescribe el metodo toString() de la clase <code>Object</code>.
     *
     * Devuelve una cadena de caracteres con el formato numerador / denominador.
     * En el caso de que el denominador sea 1 solo muestra el numerador.
     *
     * @return String con el contenido de <code>Racional</code>.
     */
	@Override
	public String toString(){
		if (esEntero()){
			return Integer.toString(getNumerador());
		} else {
			assert(!esEntero());
			return (getNumerador() + "/" + getDenominador());
		}
	}

	
	/**
	 * ******************************** Metodos Privados ********************************
	 */

	
	/**
	 * Funcion que halla el mcd de dos enteros.
	 *  
	 * @param a int Primer entero.
	 * @param b int Segundo entero.
	 * @return mcd int El mcd de a y b.
	 */
	private int mcd(int a, int b) {
	     int r;
	     while (a % b != 0) {
	       r = a % b;
	       a = b;
	       b = r;
	     }
	     return b;
	}
}
