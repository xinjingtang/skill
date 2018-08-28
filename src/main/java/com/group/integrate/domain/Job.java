package com.group.integrate.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/8/28.
 */
@Data
@TableName(value = "job")
public class Job{

    @Id
    @TableField(value = "id")
    private BigInteger id;

    @TableField(value = "is_primary_job")
    private String primaryJob;

    @TableField(value = "job_name")
    private String jobName;



    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", primaryJob='" + primaryJob + '\'' +
                ", jobName='" + jobName + '\'' +
                '}';
    }
}
