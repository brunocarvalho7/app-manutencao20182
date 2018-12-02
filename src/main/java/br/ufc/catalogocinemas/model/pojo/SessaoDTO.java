package br.ufc.catalogocinemas.model.pojo;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessaoDTO{

    private Integer id;
    private Integer filmeId;
    private Integer salaId;
    private LocalTime horario;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public SessaoDTO(Integer filmeId, Integer salaId, LocalTime horario, 
                    LocalDate dataInicio, LocalDate dataFim){
        this.filmeId = filmeId;
        this.horario = horario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public SessaoDTO(Integer id, Integer filmeId, Integer salaId, LocalTime horario, 
                    LocalDate dataInicio, LocalDate dataFim){
        this.id = id;
        this.filmeId = filmeId;
        this.horario = horario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    /**
     * @return the dataFim
     */
    public LocalDate getDataFim() {
        return dataFim;
    }

    /**
     * @return the dataInicio
     */
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    /**
     * @return the filmeId
     */
    public Integer getFilmeId() {
        return filmeId;
    }

    /**
     * @return the horario
     */
    public LocalTime getHorario() {
        return horario;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the salaId
     */
    public Integer getSalaId() {
        return salaId;
    }

}
