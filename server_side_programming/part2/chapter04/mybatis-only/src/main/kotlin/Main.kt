import database.*
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.mybatis.dynamic.sql.SqlBuilder

fun main() {
//    list5_4_4()
//    list5_4_6()
//    list5_4_8()
//    list5_4_9()
//    list5_4_10()
    list5_4_20()
}

fun createSessionFactory(): SqlSessionFactory{
    val resource = "mybatis-config.xml"
    val inputStream = Resources.getResourceAsStream(resource)
    return SqlSessionFactoryBuilder().build(inputStream)
}

fun list5_4_3() {
    createSessionFactory().openSession().use {session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val user = mapper.selectByPrimaryKey(100)
        println(user)
    }
}

// select id, name, age, profile from user where name = "Jiro";
fun list5_4_4() {
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val userList = mapper.select {
            where(UserDynamicSqlSupport.User.name, SqlBuilder.isEqualTo("Jiro"))
        }
        println(userList)
    }
}

// select id, name, age, profile from user where age >= 25;
fun list5_4_6() {
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val userList = mapper.select {
            where(UserDynamicSqlSupport.User.age, SqlBuilder.isGreaterThanOrEqualTo(25))
        }
        println(userList)
    }
}

// カウント
fun list5_4_8() {
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val count = mapper.count {
            where(UserDynamicSqlSupport.User.age, SqlBuilder.isGreaterThanOrEqualTo(25))
        }
        println(count)
    }
}

// カウント
fun list5_4_9() {
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val count = mapper.count { allRows() }
        println(count)
    }
}

// insert
fun list5_4_10() {
    val user = UserRecord(103, "Shiro", 18, "Hello")
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val count = mapper.insert(user)
        session.commit()
        println("${count}行のレコードを挿入しました")
    }
}

// 複数行のinsert
fun list5_4_12() {
    val userList = listOf(UserRecord(104, "Goro", 15, "Hello"), UserRecord(105, "Rokuro", 13, "Hello"))
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val count = mapper.insertMultiple(userList)
        session.commit()
        println("${count}行のレコードを挿入しました")
    }
}

// 主キーを検索条件としたupdate
fun list5_4_14() {
    val user = UserRecord(id = 105, profile = "hogehoge")
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val count = mapper.updateByPrimaryKeySelective(user)
        session.commit()
        println("${count}行のレコードを更新しました")
    }
}

// 主キー以外のカラムを検索条件としてupdate
fun list5_4_16() {
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val count = mapper.update {
            set(UserDynamicSqlSupport.User.profile).equalTo("Hey")
            where(UserDynamicSqlSupport.User.id, SqlBuilder.isEqualTo(104))
        }
        session.commit()
        println("${count}行のレコードを更新しました")

    }
}

// 主キー以外のカラムを検索条件としたレコード更新（Recordオブジェクトを使う場合）
fun list5_4_18() {
    val user = UserRecord(profile = "Good Morning")
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val count = mapper.update {
            updateSelectiveColumns(user)
            where(UserDynamicSqlSupport.User.name, SqlBuilder.isEqualTo("Shiro"))
        }
        session.commit()
        println("${count}行のレコードが更新されました")
    }
}

// Delete
fun list5_4_20() {
    createSessionFactory().openSession().use { session ->
        val mapper=session.getMapper(UserMapper::class.java)
        val count = mapper.deleteByPrimaryKey(102)
        session.commit()
        println("${count}行のレコードを削除しました")
    }
}