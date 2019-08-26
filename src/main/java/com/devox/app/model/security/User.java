package com.devox.app.model.security;

import com.devox.app.model.Entities.District;
import com.devox.app.model.Entities.Language;
import com.devox.app.model.Entities.MeterReading;
import com.devox.app.model.Entities.WaterMeter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER"
        , uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "USERNAME"
        }),
        @UniqueConstraint(columnNames = {
                "EMAIL"
        })
})
public class User {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
//    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USERNAME", length = 255, unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @Column(name = "FIRSTNAME", nullable = false, length = 255)
    private String firstname;

    @Column(name = "LASTNAME", nullable = false, length = 255)
    private String lastname;

    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @Column(name = "PARENTID", nullable = true, length = 20)
    private String parentid;
    //bi-directional many-to-one association to District
    @ManyToOne
    @JoinColumn(name = "districtid", nullable = false)
    private District district;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities;

    //bi-directional many-to-one association to MeterReading
    @OneToMany(mappedBy = "user")
    private List<MeterReading> meterReadings;

    //bi-directional many-to-one association to Language
    @ManyToOne
    @JoinColumn(name="languageid", nullable=false)
    private Language language;

    public User() {
    }

    public User(@NotNull String username, @NotNull String password,
                @NotNull String firstname, @NotNull String lastname,
                @NotNull String email, @NotNull Boolean enabled,
                @NotNull Date lastPasswordResetDate, String phone,
                String parentId, Set<Authority> authorities,
                District district , Language language) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.phone = phone;
        this.authorities = authorities;
        this.district = district;
        this.parentid = parentId;
        this.language=language;
    }

    public User(@NotNull String username, @NotNull String password, @NotNull String firstname, @NotNull String lastname, @NotNull String email, @NotNull Boolean enabled, String phone, @NotNull Date lastPasswordResetDate) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }


    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<MeterReading> getMeterReadings() {
        return this.meterReadings;
    }

    public void setMeterReadings(List<MeterReading> meterReadings) {
        this.meterReadings = meterReadings;
    }

    public MeterReading addMeterReading(MeterReading meterReading) {
        getMeterReadings().add(meterReading);
        meterReading.setUser(this);

        return meterReading;
    }

    public MeterReading removeMeterReading(MeterReading meterReading) {
        getMeterReadings().remove(meterReading);
        meterReading.setUser(null);

        return meterReading;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
