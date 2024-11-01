package com.y2k.hospital.service.interf;

import com.y2k.hospital.dto.MedicoDto;
import com.y2k.hospital.dto.Response;

public interface MedicoService {
   Response registerMedico(MedicoDto medicoDto);
   Response get();
   Response getByCi(Long ci);
   Response update(Long ci, MedicoDto medicoDto);
   Response delete(Long ci);
}
