﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exercicio3
{
    public class FolhaPagamento : IFolhaPagamento
    {
        public Demonstrativo GerarDemonstrativo(int horasCategoria, double salarioBase, double horasExtras, double horasDescontadas)
        {
            #region Variáveis
            double valorHora = salarioBase / horasCategoria;
            HorasCalculadas hExtras = new HorasCalculadas(horasExtras, TruncaValor(valorHora * horasExtras));
            HorasCalculadas hDescontadas = new HorasCalculadas(horasDescontadas, TruncaValor(valorHora * horasDescontadas));
            double totalProventos = TruncaValor(salarioBase + hExtras.ValorTotalHoras - hDescontadas.ValorTotalHoras);
            Desconto inss = CalculaINSS(totalProventos);
            Desconto irpf = CalculaIRPF(totalProventos, inss);
            double totalDescontos = TruncaValor(inss.Valor + irpf.Valor);
            double aliquotaFgts = 11;
            Desconto fgts = new Desconto(aliquotaFgts, TruncaValor((totalProventos * aliquotaFgts) / 100));
            #endregion

            Demonstrativo dmst = new Demonstrativo(
                TruncaValor(salarioBase), // salário base
                TruncaValor(horasCategoria), // horas categoria
                hExtras, // valor horas extras
                hDescontadas, // valor horas descontadas
                totalProventos, // total proventos
                inss, // inss
                irpf, // irpf
                totalDescontos, // total de descontos
                TruncaValor(totalProventos - totalDescontos), // salário liquido
                fgts // fgts
            );
            return dmst;
        }

        private static Desconto CalculaINSS(double totalProventos)
        {
            var aliquota = 0;
            if (totalProventos <= 1000)
                aliquota = 8;
            else if (totalProventos <= 1500)
                aliquota = 9;
            else
                aliquota = 10;
            return new Desconto(aliquota, TruncaValor((totalProventos * aliquota) / 100));
        }

        private static Desconto CalculaIRPF(double totalProventos, Desconto inss)
        {
            double aliquota = 0;
            double valor = (totalProventos - inss.Valor);
            if (valor <= 1710.78)
                return new Desconto(0, 0);
            else if (valor <= 2563.91)
                aliquota = 7.5;
            else if (valor <= 3418.59)
                aliquota = 15;
            else if (valor <= 4271.59)
                aliquota = 22.5;
            else
                aliquota = 27.5;
            return new Desconto(aliquota, TruncaValor((valor * aliquota) / 100));
        }

        private static double TruncaValor(double valor)
        {
            return Math.Truncate(valor * 100) / 100;
        }
    }
}
