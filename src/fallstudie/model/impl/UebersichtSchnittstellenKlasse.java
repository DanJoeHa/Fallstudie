package fallstudie.model.impl;

import java.util.Collection;
/**
 * @author Phil
 * @date 20.10.2013
 * Klasse beinhaltet die Anzahl der verschiedenen Arten einer Jahres/Wochenübersicht,
 * welche benötigt wird um das Array mit den Richtigen Dimensionen zu befüllen.
 * Außerdem beinhaltet diese Klasse alle Jahresuebersichten-Objekte als Collection und die Wochenuebersichten-Objekte als Collection,
 * welche durch die Methoden getAlleJahresuebersichtenZumBereich/zuAllenBereichen() und getAlleWochenuebersichtenZumBereich/zuAllenBereichen() befüllt wird.
 */
public class UebersichtSchnittstellenKlasse {
	public int anzahlArten;
	public Collection<Jahresuebersicht> Jahresuebersichten;
	public Collection<Wochenuebersicht> Wochenuebersichten;
}