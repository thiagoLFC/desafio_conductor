package br.com.conductor.desafio.comum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe para tratar as Strings
 * @author thiag
 *
 */
public class StringUtil {
	
	private static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

	/**
	 * Metodo para validar se o email e valido ou nao
	 * @param email Dados do email
	 * @return true caso seja valido, false caso nao seja
	 */
	public static boolean isEmailValido(String email) {
	    Matcher matcher = emailPattern.matcher(email);
	    return matcher.matches();
	}

}
