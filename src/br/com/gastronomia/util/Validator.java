package br.com.gastronomia.util;

public class Validator {
	public static class validaCpf {
		public static boolean fazConta(String cpf) {
			try {
				return fazConta(cpf, 10) && fazConta(cpf, 11);
			} catch (Exception e) {
				return false;
			}
		}

		public static boolean fazConta(String cpf, int daVez) {
			double somatorio = 0;
			for (int i = 0; i < daVez - 1; i++)
				somatorio = somatorio + (Double.parseDouble(cpf.substring(i, i + 1)) * (daVez - i));

			somatorio = (somatorio * 10) % 11;
			if (somatorio == 10)
				somatorio = 0;
			if (Double.parseDouble(cpf.substring(daVez - 1, daVez)) == ((somatorio)))
				return true;

			return false;
		}
	}

}
