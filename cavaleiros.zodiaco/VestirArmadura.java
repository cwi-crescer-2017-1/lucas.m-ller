public class VestirArmadura implements Movimento {
    private Saint saint;
    
    public VestirArmadura(Saint saint) {
        this.saint = saint;
    }

    public void executar() {
        this.saint.vestirArmadura();
    }
    
    public boolean movimentoTiraDano() {
        return false;
    }
    
    public boolean equals(Object object) {
        boolean equal = false;
        if(object != null && object instanceof VestirArmadura) {
            VestirArmadura v = (VestirArmadura) object;
            equal = this.saint.equals(v.saint);
        }
        return equal;
    }
}