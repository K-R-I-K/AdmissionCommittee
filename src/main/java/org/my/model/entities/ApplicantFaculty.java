package org.my.model.entities;

import java.util.List;

public class ApplicantFaculty {
    private long id;
    private long userId;
    private long facultyId;
    private int mark1;
    private int mark2;
    private int mark3;
    private double avgCertificateMark;
    private boolean passed;
    {
        passed = false;
    }

    public ApplicantFaculty() {
    }

    public ApplicantFaculty(ApplicantFaculty applicantFaculty) {
        this.id = applicantFaculty.id;
        this.userId = applicantFaculty.userId;
        this.facultyId = applicantFaculty.facultyId;
        this.mark1 = applicantFaculty.mark1;
        this.mark2 = applicantFaculty.mark2;
        this.mark3 = applicantFaculty.mark3;
        this.avgCertificateMark = applicantFaculty.avgCertificateMark;
        this.passed = applicantFaculty.passed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    public double getAvgCertificateMark() {
        return avgCertificateMark;
    }

    public void setAvgCertificateMark(double avgCertificateMark) {
        this.avgCertificateMark = avgCertificateMark;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public static ApplicantFaculty.Builder builder() {
        return new ApplicantFaculty().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public ApplicantFaculty.Builder id(Long id) {
            ApplicantFaculty.this.id = id;
            return this;
        }

        public ApplicantFaculty.Builder userId(long userId) {
            ApplicantFaculty.this.userId = userId;
            return this;
        }

        public ApplicantFaculty.Builder facultyId(long facultyId) {
            ApplicantFaculty.this.facultyId = facultyId;
            return this;
        }

        public ApplicantFaculty.Builder mark1(int mark1) {
            ApplicantFaculty.this.mark1 = mark1;
            return this;
        }

        public ApplicantFaculty.Builder mark2(int mark2) {
            ApplicantFaculty.this.mark2 = mark2;
            return this;
        }

        public ApplicantFaculty.Builder mark3(int mark3) {
            ApplicantFaculty.this.mark3 = mark3;
            return this;
        }

        public ApplicantFaculty.Builder avgCertificateMark(double avgCertificateMark) {
            ApplicantFaculty.this.avgCertificateMark = avgCertificateMark;
            return this;
        }

        public ApplicantFaculty.Builder passed(boolean passed) {
            ApplicantFaculty.this.passed = passed;
            return this;
        }

        public ApplicantFaculty build() {
            return ApplicantFaculty.this;
        }
    }
}
