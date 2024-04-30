package com.scamps.ClubService.models;

import com.scamps.ClubService.models.generics.GenericEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "courts")
public class Court implements GenericEntity<Court> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courtId;

    @NotBlank
    private String name;

    @NotNull
    private int courtType;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    public Court() {
    }

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourtType() {
        return courtType;
    }

    public void setCourtType(int courtType) {
        this.courtType = courtType;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
