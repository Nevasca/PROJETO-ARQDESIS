	/*public static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
	public static final String EMAIL_REGEX2 = ".+@.+\\.[a-z]+";	
	
	public boolean validarTextField(String txt)
	{
		boolean r = true;
		
		char[] t = null;
		txt.replace(" ","");
		
		if(txt.trim().length() < 4)
		{
			 r = false;
			 
		}else
		{
			
			t = txt.toCharArray();
			
			for(int i = 0;i < t.length;i++)
			{
				
				if(t[i] >= '0' && t[i] <= '9')
				{
					r = false;
					break;
				}
				
			}	
			
		}
		
		return r;
 		
	}
	
	public boolean validarTxtNull(String txt){

		boolean r = false;		
		txt.replace(" ","").trim();
		
		if(txt.trim() == ""  || txt.trim().length() <= 0)
		{
			 r = false;
			 
		}else{
			
			r = true;
		}
		
		return r;
		
	}
 
	public boolean validarComboBox(JComboBox<String> cb)
	 {
	  
		boolean r = true;
	    
	    if(cb.getSelectedIndex() < 1)
	    { 
	  	   r = false;
	    }
	    
	    return r;
	 }
       
	public boolean validaNum(String num)
		{
			boolean r = true;
			int a;
			num.replace(" ","");
			 a = num.trim().length();
			 
				if (a < 1 || num == ""){ 
		        	r = false;
		        }
		        else
		        {
		        	char [] parte=num.toCharArray();
		        	for ( int i = 0; i < parte.length; i++ )
			        {  
				        if ( !Character.isDigit( parte[ i ] ) ) 
				        {  
				            r = false;  
				            break;  
				        }  
			        }
		        }
				
			return r;
		}
		
	public boolean validaRadioBut(JRadioButton rb)
		{
			boolean r = true;
			
			if(rb.isSelected())
			{
				
				r = true;
				
			}else
			{
				 
				r = false;
			}
			

			return r;
		}
		
	public boolean validarList(int i)
		{
			
			boolean r = true;
			
			if(i == 0){
				
				r = false;
			}
			
			
			return r;
		}
		
	public boolean validaRg(String rg)
		{
		
			boolean r = true;
			
			if (rg.length() >=  5 && rg.length() <= 12)
			{
				r = true;
				
			}else{
				
				r = false;
			}
			
			return r;
		}

	public boolean validaTel(String tel)
		{
		      
	        if (tel.equals("(11)1111-1111") ||  tel.equals("(22)2222-2222") || tel.equals("(33)3333-3333") || tel.equals("(44)4444-4444") || tel.equals("(55)5555-5555") || tel.equals("(66)6666-6666") ||
	        		tel.equals("(77)7777-7777") || tel.equals("(88)8888-8888") || tel.equals("(99)9999-9999") || tel.equals("(00)0000-0000"))
	        	{	      
	        			return false;
	        	}
	       	      
		      if((tel == null) || (tel.trim().length() != 13)) 
		      {
		         return false; 
		      }
		      else
		      {  
		         return true; 
		      }
		      
		}
		
	public boolean validaCombo(JComboBox<String> cb){
				boolean r = true;
				
				if(cb.getSelectedIndex()<1)
				{
					
						r = false;
				}
				
				return r;
	    }

	public boolean validaCpf(String cpf)
		{ 
	        int resultP = 0;  
	        int resultS = 0; 
	             
	        if (cpf.equals("111.111.111-11") ||  cpf.equals("222.222.222-22") || cpf.equals("333.333.333.-33") || cpf.equals("444.444.444-44") || cpf.equals("555.555.555-55") || cpf.equals("666.666.666-66") ||
	        		cpf.equals("777.777.777-77") || cpf.equals("888.888.888-88") || cpf.equals("999.999.999-99") || cpf.equals("000.000.000-00"))
	        	{	      
	        			return false;
	        	}
	       		
	        
	        if (cpf == null || cpf.trim().length() < 14){
	        	return false;
	        }
	        	 
	        String c = cpf.substring(0, 3);
	        String d = cpf.substring(4, 7);
	        String g = cpf.substring(8, 11);
	        String f = cpf.substring(12, 14);
	        cpf = c + d + g + f; 
	        
	        int[] cpfNum = new int[cpf.length()]; 
	  
	        //converte a string para um array de integer  
	        for (int i = 0; i < cpfNum.length; i++) {  
	            cpfNum[i] = Integer.parseInt(cpf.substring(i, i + 1));  
	        }  
	  
	        for (int i = 0; i < 9; i++)
	        {  
	            resultP += cpfNum[i] * (i + 1);  
	        }  
	        int divP = resultP % 11;  
	  
	        if (divP != cpfNum[9]) 
	        {  
	            return false; 
	            
	        } else {  

	        	for (int i = 0; i < 10; i++)
	            {  
	                resultS += cpfNum[i] * (i);  
	            }  
	            int divS = resultS % 11;  
	  
	            if (divS != cpfNum[10])
	            {  
	                return false;  
	            }  
	        }  
	  
	        return true;  
	    }

	public boolean validarEmail(String email)
	 {
		 boolean r = false;
			
		 if(email == null || email.trim().length() == 0){
			r = false;
		 }
	  		 //String email = "erica@hotmail.com";
		     //Definir a seqüência de padrão de e-mail	
	  		Pattern pattern = Pattern.compile(EMAIL_REGEX,Pattern.CASE_INSENSITIVE);		 	  		
	  		Matcher matcher = pattern.matcher(email);

	  		  // Verifica se o Match jogado foi encontrado
	  		  if(matcher.matches()){
	  			  r = true;
	  		  }
	  		  else{

	  		     r = false;
	  		  }
		 		
		 return r;
 
	  	}
	
	public boolean validaPreco(String num)
    {
		    boolean r = true;

		    if (num.trim().length() < 3)
		    { 
		        r = false;
		
		    }else if(num.equals("0.00"))
		    { 
		       r = false;
		       
		    }else{
		    	
		    	r = true;
		    }
	
		    return r; 
    }
}*/