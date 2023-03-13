public class SudokuLöser {
    private Block[][] block;
    private final int groesse = 9;
    private int spalte = 0;
    private int reihe = 0;
    long startTime = System.currentTimeMillis();

    public SudokuLöser(Block[][] block) {
        this.block = block;

    }

    public boolean überprüfen(int reihe, int spalte, int zahl) {
        // Überprüfung der Reihe
        for (int j = 0; j < groesse; j++) {
            if (j != spalte && block[reihe][j].gibWert() == zahl) {
                return false;
            }
        }
        // Überprüfung der Spalte
        for (int i = 0; i < groesse; i++) {
            if (i != reihe && block[i][spalte].gibWert() == zahl) {
                return false;
            }
        }
        // Überprüfung des Blocks
        int blockStartReihe = (reihe / 3) * 3;
        int blockStartSpalte = (spalte / 3) * 3;
        for (int i = blockStartReihe; i < blockStartReihe + 3; i++) {
            for (int j = blockStartSpalte; j < blockStartSpalte + 3; j++) {
                if (i != reihe || j != spalte) {
                    if (block[i][j].gibWert() == zahl) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void zurück() {
        if(spalte==0 && reihe==0){
            System.out.println("unlösbar");
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Laufzeit: " + totalTime + " Millisekunden");
            System.exit(0);
        }
        if (spalte == 0) {
            reihe--;
            spalte = groesse - 1;
        } else {
            spalte--;
        }
        Block bl = block[reihe][spalte];
        if (bl.istFest()) {
            zurück();
        } 
    }

    public void lösen() {
        System.out.println("Lösvorgang startet");
        while (reihe < groesse) {
            Block b = block[reihe][spalte];
            //System.out.println("Startet mit Reihe "+reihe+" Spalte "+spalte);
            if(!b.istFest()) {
                //System.out.println("Block ist nicht fest. Werte werden jetzt geändert");
                b.nächster();
                while(b.gibWert() != -1 && !überprüfen(reihe, spalte, b.gibWert())) {
                    b.nächster();
                    //System.out.println("Nächster Wert");
                }
                //System.out.println("Fertig mit den Werten");
                if(b.gibWert()==-1){
                    //System.out.println("Block gibt -1");
                    b.reset();
                    //System.out.println("Reihe "+reihe+" spalte "+spalte);
                    zurück();
                    b.reset();
                    zurück();
                    //System.out.println("Reihe wurde zu "+ reihe+" und Spalte zu"+spalte+" geändert");
                }
            }
            spalte++;
            if (spalte == groesse) {
                spalte = 0;
                reihe++;
            }
            //System.out.println("Reihe und Spalte wurde zu "+reihe +" "+ spalte);
        }

        System.out.println("Lösung gefunden:");
        for (int i = 0; i < groesse; i++) {
            for (int j = 0; j < groesse; j++) {
                System.out.print(block[i][j].gibWert() + " ");
            }
            System.out.println("");
        }
    }
}