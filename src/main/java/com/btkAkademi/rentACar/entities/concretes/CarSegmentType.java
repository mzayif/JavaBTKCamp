package com.btkAkademi.rentACar.entities.concretes;

import com.btkAkademi.rentACar.business.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_segment_type")
public class CarSegmentType extends BaseEntity {

    @Column(name = "segment_name")
    private String segmentName;

    @Column(name = "car_type")
    private CarType carType;

    @OneToMany(mappedBy = "carSegmentType")
    private List<Car> cars;
}
