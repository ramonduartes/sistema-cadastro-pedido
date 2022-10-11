package br.com.wmw.comprastc.util;

import totalcross.io.File;
import totalcross.sys.Time;
import totalcross.sys.Vm;
import totalcross.util.Date;

public class Erro
{
   public static void trataExcecao(Throwable t)
   {
      t.printStackTrace();
      try
      {
         File f = new File("erros.txt",File.CREATE);
         String nome = t.getClass().getName();
         String msg = t.getMessage();
         String st = Vm.getStackTrace(t);
         f.setPos(f.getSize()); // vai pro final
         f.writeBytes("========================\n"+new Date()+" "+new Time()+"\n"
               + "Nome: "+nome+"\nMensagem: "+msg+"\nStack trace: "+st);
         f.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}

