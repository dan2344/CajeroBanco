package cajero;


public class Regulador {
    Usuario user;
    String Operacion;
    int SaldoFinal =0;
    ///-----------------------Ejecuta las operaciones -----------------------\\\\
    public Regulador(Usuario user) {
        this.user = user;
    }    
    public Regulador(String Operacion, int Cantidad) {
        this.Operacion = Operacion;
        if(Operacion.equals("Depositar")){
             SaldoFinal=user.getSaldo() + Cantidad;
            user.setSaldo(SaldoFinal);
        }else if(Operacion.equals("Retirar")){
            SaldoFinal=user.getSaldo() - Cantidad;
            user.setSaldo(SaldoFinal);
        }
    }
}
