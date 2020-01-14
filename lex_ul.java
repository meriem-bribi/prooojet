package meriem;

import java.util.ArrayList;
import java.util.List;

public class lex_ul {
	
	static ArrayList<String> mots = new ArrayList<String>();
	static ArrayList<String> lignes = new ArrayList<String>();
	static ArrayList<String> sortie_lexic = new ArrayList<String>();
	static String[] mot;
	
	public boolean isNum(String chaine, int i) {
		char[] nombre = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		int j = 0;
		while (j < nombre.length) {
			if (chaine.charAt(i) == nombre[j]) {
				return true;
			}
			j++;
		}

		return false;
	}

	public String num(String chaine) {
		int i = 0;
		int token_pos = 0;
		boolean point_unique = true;
		while (i < chaine.length()) {
			if (isNum(chaine, i)) token_pos++;
			else if(point_unique & chaine.charAt(token_pos)=='.') {
				token_pos++;
				point_unique = false;
			}
			i++;
		}
		
		if (token_pos == chaine.length() && !chaine.contains(".")) return "Nombre entier";
		else if (token_pos == chaine.length() && !point_unique) return "Nombre reel";
		return null;

	}
	

	public boolean isChar(String chaine, int i) {
		char[] alphabet = { 'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i',
				'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T',
				't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z' };
		int k = 0;
		while (k < alphabet.length) {
			if (chaine.charAt(i) == alphabet[k]) {
				return true;
			}
			k++;
		}
		return false;

	}

	public String id(String chaine) {
		boolean verifier_Premier = false;
		boolean tiret_unique = true;
		int token_pos = 0;
		int i = 0;
		if (isChar(chaine, 0)) {
			token_pos++;
			verifier_Premier = true;
		}
		if (verifier_Premier == true && chaine.length() == 1)
			return "identificateur";
		
		else if (chaine.length() > 1) {
			i = 1;
			while (i < chaine.length()) {
				
				if (isChar(chaine, i))
					{token_pos++;
					tiret_unique=true;
					}
				else if (isNum(chaine, i))
					{token_pos++;
					tiret_unique=true;
					}
				else if (chaine.charAt(i) == '_' && tiret_unique) {
					tiret_unique=false;
					token_pos++;
				}
				i++;
			}
			if (token_pos == chaine.length())
				return "Identificateur";
		}
		return null;
	}
	


	public String UL_reserve(String chaine) {
		String[] mot_reserve = { "String", "\"", "<", ">", ":", ",", "Start_Program", "Int_Number", ";;", "Give", "Real_Number", "If", "--", "Else",
				"Start", "Affect", "to", "Finish", "ShowMes :", "ShowVal", "//.", "End_Program" };

		String[] Affichage = { "Declaration d'une chaine de caractere", "mot reserve pour une chaine de caractere",
				"symbol inferieur", "symbol superieur", "caractere reservé deux points", "caractere reservé virgule",
				"Mot reserve debut du programme", " Mot reserve debut declaration d'un entier",
				"Mot reserve fin instruction", "Mot reserve pour affectation entre variables", " Mot reserve debut declaration d'un Real",
				" Mot reserve pour condition SI", "Mot reserve pour condition", "Mot reserve pour condition SINON", "Debut d'un sous programme",
				"Mot reserve pour affectation", "Mot reserve pour affectation", "Fin d'un sous programme", "Mot reservé pour afficher un message", 
				"Mot reservé pour afficher une valeur", "Mot reservé pour un commentaire", "Mot reserve Fin du programme" };
		int i = 0;
		while (i < mot_reserve.length) {
			if (chaine.equals(mot_reserve[i])) {
				return Affichage[i];
			}
			i++;
		}
		return null;
	}

	
	public void lexicale(List<String> liste) {
		int i = 0;
		
		while (i < mots.size()) {
			if (UL_reserve(mots.get(i)) != null) {
				sortie_lexic.add(UL_reserve(mots.get(i)));
			} else if (id(mots.get(i)) != null) {
				sortie_lexic.add(id(mots.get(i)));
			} else if (num(mots.get(i)) != null) {
				sortie_lexic.add(num(mots.get(i)));
			} 
			else sortie_lexic.add("Erreur");

			i++;
		}

	}


}
