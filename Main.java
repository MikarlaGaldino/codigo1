import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Funcionario {
    protected String nome;
    protected int horasTrabalhadas;
    protected double valorHora;

    
    public Funcionario(String nome, int horasTrabalhadas, double valorHora) {
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = valorHora;
    }

   
    public double calcularPagamento() {
        return horasTrabalhadas * valorHora;
    }
}


class FuncionarioProprio extends Funcionario {
    
    public FuncionarioProprio(String nome, int horasTrabalhadas, double valorHora) {
        super(nome, horasTrabalhadas, valorHora);
    }

   
}


class FuncionarioTerceirizado extends Funcionario {
    private double despesaAdicional;

    
    public FuncionarioTerceirizado(String nome, int horasTrabalhadas, double valorHora, double despesaAdicional) {
        super(nome, horasTrabalhadas, valorHora);
        this.despesaAdicional = despesaAdicional;
    }

   
    @Override
    public double calcularPagamento() {
        double pagamentoBase = super.calcularPagamento();
        return pagamentoBase + (despesaAdicional * 1.1); 
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantos funcionários deseja cadastrar? ");
        int n = scanner.nextInt();

        List<Funcionario> funcionarios = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("\nFuncionário #" + (i + + 1));

            System.out.print("Tipo (P/T): ");
            char tipo = scanner.next().charAt(0);

            System.out.print("Nome: ");
            String nome = scanner.next();

            System.out.print("Horas trabalhadas: ");
            int horasTrabalhadas = scanner.nextInt();

            System.out.print("Valor por hora: ");
            double valorHora = scanner.nextDouble();

            if (tipo == 'P') {

                FuncionarioProprio fp = new FuncionarioProprio(nome, horasTrabalhadas, valorHora);
                funcionarios.add(fp);
            } else if (tipo == 'T') {

                System.out.print("Despesa adicional: ");
                double despesaAdicional = scanner.nextDouble();

                FuncionarioTerceirizado ft = new FuncionarioTerceirizado(nome, horasTrabalhadas, valorHora, despesaAdicional);
                funcionarios.add(ft);
            } else {
                System.out.println("Tipo inválido. Digite P para próprio ou T para terceirizado.");
                i--; 
            }
        }

        System.out.println("\nPagamentos:");

        
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.nome + ": R$" + funcionario.calcularPagamento());
        }

        scanner.close();
}
}
