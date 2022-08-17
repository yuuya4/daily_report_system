package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "like.getAllMine",
            query = "SELECT l FROM Like AS l WHERE l.report = :report ORDER BY l.id DESC"),
    @NamedQuery(
            name = "like.countAllMine",
            query = "SELECT COUNT(l) FROM Like AS l WHERE l.report = :report"),
    @NamedQuery(
            name = "like.countAll",
            query = "SELECT COUNT(l) FROM Like AS l WHERE l.employee = :employee AND l.report =:report")
})

/**
 * いいねデータのDTOモデル
 *
 */
@Table(name = "likes")


@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)


public class Like {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;
    /**
     * 登録日時
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = "upate_at", nullable = false)
    private LocalDateTime updatedAt;

}