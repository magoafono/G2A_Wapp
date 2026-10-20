# Versione applicativa e identificazione del WAR

La versione dell'app è **1.Y.Z**, definita una sola volta in `G2A_Wapp/pom.xml`.
Non è la versione di PrimeFaces o eXist e non viene ricavata dall'ultimo commit
presente sul server: identifica il codice effettivamente impacchettato nel WAR.

- **X = 1** per questa linea di lavoro. Non aumentarlo senza una scelta esplicita.
- **Y** aumenta per nuove funzionalità o modifiche funzionali/UI; **Z riparte da0**.
- **Z** aumenta per bugfix, incluse correzioni di sicurezza senza nuove funzionalità.
- Incrementare una volta per ogni modifica logica completata, prima del commit/build
  di collaudo. Non per ogni file salvato o tentativo di compilazione.
- Documentazione/tooling senza cambi di comportamento applicativo non richiedono
  automaticamente un nuovo numero. Dati e credenziali esterne non sono codiceapp.

```bash
python3 tools/bump-version.py fix      # 1.1.0 -> 1.1.1
python3 tools/bump-version.py feature  # 1.1.1 -> 1.2.0
# --dry-run mostra il cambiamento senza applicarlo.
```

La prima versione con questa politica è **1.1.0**. Non vengono rinumerati i vecchi
checkpoint/backup o la baselinePF4.

## Build sempre distinguibile

Maven filtra il resourcebundle `messages.properties` una sola volta e incorpora:
versione dalPOM e data/oraUTC della build. Tutti gli header principali, il template
Plotino e Welcome condividono questi valori del WAR.

Esempio: `v.1.1.0 · build 2026-10-20 10:15:30 UTC`.
Il tooltip mostra il commit. Gli script locali build-war.sh/build-candidate.sh
iniettano automaticamente il Gitabbrev, aggiungendo `-dirty` se il modulo contiene
modifiche non committate. Niente dipendenza Git sul server a runtime. Un semplice
`mvn package` senza wrapper conserva versione/timestamp ma revision=`unknown`, non
inventa una provenienza. Per build/versione riproducibili registrare l'hashSHA del
WAR nel report: il timestamp rende i pacchetti nuovamente compilati diversi.

Il numeroY/Z non può essere classificato affidabilmente dal semplice conteggio
commit: va scelto in base al tipo di cambiamento. Il timestamp cambia comunque a
ogni nuova build, anche se qualcuno dimentica il bump. Non promette che quella
visibile sia l'ultima disponibile suGitHub: mostra quella realmente in esecuzione.

Per tutti i prossimi interventi applicativi di agenti/sviluppatori: effettuare il
bump appropriato; non reintrodurre versioni statiche negli XHTML/resourcebundle.
