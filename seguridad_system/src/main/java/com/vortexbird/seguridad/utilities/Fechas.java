package com.vortexbird.seguridad.utilities;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class Fechas
{

    public Fechas()
    {
    }

    public static Date strToDate(String strFecha, String patron)
    {
        Date convertDate = null;
        try
        {
            convertDate = (new SimpleDateFormat(patron)).parse(strFecha);
            return convertDate;
        }
        catch(ParseException pex)
        {
            pex.printStackTrace();
        }
        return null;
    }

    public static String dateToStr(Date fecha, String patron)
    {
        try
        {
            Format formatter = new SimpleDateFormat(patron);
            return formatter.format(fecha);
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder("Error en dateToStr (")).append(fecha).append(",").append(patron).append("): ").append(e.toString()).toString());
        }
        return "";
    }

    
    public static double diasEntreFechas(Date fechafinal, Date fechainicial)
    {
        double Ndias = 0.0D;
        try
        {
            Ndias = (fechafinal.getTime() - fechainicial.getTime()) / 0x5265c00L;
            return Ndias;
        }
        catch(Exception e)
        {
            return 0.0D;
        }
    }

    public static Date sumar(Date fecha, int dias, int meses, int aF1os, int semanas)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            c.add(5, dias);
            c.add(2, meses);
            c.add(1, aF1os);
            c.add(5, semanas * 7);
            fecha = c.getTime();
            return fecha;
        }
        catch(Exception e)
        {
            System.out.println("Error tratando de sumar dias a fecha");
        }
        return new Date();
    }
    
    public static Date sumarConMinutos(Date fecha, int dias, int meses, int aF1os, int semanas, int minutos)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            c.add(5, dias);
            c.add(2, meses);
            c.add(1, aF1os);
            c.add(5, semanas * 7);
            c.add(Calendar.MINUTE,minutos);
            fecha = c.getTime();
            return fecha;
        }
        catch(Exception e)
        {
            System.out.println("Error tratando de sumar dias a fecha");
        }
        return new Date();
    }

    public static int getDiaDelMes(Date fecha)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(5);
    }

    public static int getDiaDeLaSemana(Date fecha)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(7);
    }

    public static int getSemanaDelAF1o(Date fecha)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(3);
    }

    public static Date sumar(Date fecha, int dias, int meses, int aF1os, int semanas, int diaDeLaSemanaQueQueda)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            c.add(5, dias);
            c.add(2, meses);
            c.add(1, aF1os);
            c.add(5, semanas * 7);
            c.set(7, diaDeLaSemanaQueQueda);
            fecha = c.getTime();
            System.out.println((new StringBuilder("Quedo en ")).append(dateToStr(fecha, "dd/MMMM/yyyy")).toString());
            return fecha;
        }
        catch(Exception e)
        {
            System.out.println("Error tratando de sumar dias a fecha");
        }
        return new Date();
    }

    public static Date moverHastaDiaDeLaSemana(Date fecha, int dia, boolean retrocediendo)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            if(!retrocediendo && dia < c.get(7))
            {
                fecha = sumar(fecha, 0, 0, 0, 1);
                c.setTime(fecha);
            }
            c.set(7, dia);
            return c.getTime();
        }
        catch(Exception e)
        {
            System.out.println("Error tratando de moverHastaDiaDeLaSemana una fecha");
        }
        return new Date();
    }

    public static Date moverHastaDiaDeLaSemana(Date fecha, int dia, boolean retrocediendo, int semanasStep)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            if(!retrocediendo && dia < c.get(7))
            {
                fecha = sumar(fecha, 0, 0, 0, 1 * semanasStep);
                c.setTime(fecha);
            }
            c.set(7, dia);
            return c.getTime();
        }
        catch(Exception e)
        {
            System.out.println("Error tratando de moverHastaDiaDeLaSemana una fecha");
        }
        return new Date();
    }

    public static Date setDayOfTheWeek(Date fecha, int dia)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            c.set(7, dia);
            return c.getTime();
        }
        catch(Exception e)
        {
            System.out.println("Error tratando de setDayOfTheWeek");
        }
        return new Date();
    }

    public static Date moverHastaDiaDelMes(Date fecha, int dia, boolean retrocediendo)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            if(!retrocediendo && dia < c.get(5))
            {
                fecha = sumar(fecha, 0, 1, 0, 0);
                c.setTime(fecha);
            }
            for(; !ExisteDiaEnMes(dia, c.get(2), c.get(1)); dia--);
            c.set(5, dia);
            return c.getTime();
        }
        catch(Exception e)
        {
            System.out.println("Error tratando de moverHastaDiaDelMes una fecha");
        }
        return new Date();
    }

    public static boolean ExisteDiaEnMes(int dia, int mes, int aF1o)
    {
        if(mes == 1)
        {
            if(aF1o % 4 == 0)
                return dia >= 1 && dia <= 29;
            return dia >= 1 && dia <= 28;
        }
        if(mes == 0 || mes == 2 || mes == 4 || mes == 6 || mes == 7 || mes == 9 || mes == 11)
            return dia >= 1 && dia <= 31;
        return dia >= 1 && dia <= 30;
    }

    public static Date moverHastaPrimerDiaDelMes(Date fecha)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            c.set(5, 1);
            return c.getTime();
        }
        catch(Exception e)
        {
            System.out.println("Error tratando de moverHastaDiaDelMes una fecha");
        }
        return new Date();
    }

    public static Date moverHastaDiaDelAF1o(Date fecha, int dia, int mes, int aF1o, boolean retrocediendo)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            if(aF1o == -1)
                aF1o = c.get(1);
            if(!retrocediendo && dia < c.get(5))
            {
                fecha = sumar(fecha, 0, 1, 0, 0);
                c.setTime(fecha);
            }
            c.set(1, aF1o);
            c.set(2, mes);
            for(; !ExisteDiaEnMes(dia, c.get(2), c.get(1)); dia--);
            c.set(5, dia);
            return c.getTime();
        }
        catch(Exception e)
        {
            System.out.println("Error tratando de moverHastaDiaDelMes una fecha");
        }
        return new Date();
    }
    
    public static void main(String[] args) {
		Fechas f = new Fechas();
		Date d = f.strToDate("2012-12-13 12:19:16.0", "yyyy-MM-dd HH:mm:ss");
		System.out.println(d);
				
	}
    
}
