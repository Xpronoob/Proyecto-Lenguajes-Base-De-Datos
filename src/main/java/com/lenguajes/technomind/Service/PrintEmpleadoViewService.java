package com.lenguajes.technomind.Service;

import com.lenguajes.technomind.entity.PrintEmpleadoView;
import com.lenguajes.technomind.repository.PrintEmpleadoViewRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PrintEmpleadoViewService {
    private final PrintEmpleadoViewRepository pevR;
    
    public PrintEmpleadoViewService(PrintEmpleadoViewRepository pevR){
        this.pevR = pevR;
    }
    
    public List<PrintEmpleadoView> obtenerPevR(){
        return pevR.findAll();
    }
}
