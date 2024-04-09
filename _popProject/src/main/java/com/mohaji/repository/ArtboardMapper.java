package com.mohaji.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mohaji.model.Artboard;

@Mapper
public interface ArtboardMapper {
	
	@Select("select count(*) from artboard")
	int count();
	
	@Select("select * from artboard")
	List<Artboard> selectAllArtboard();
	
	@Select("select * from artboard where POP_CODE =#{popCode}")
	Artboard selectOneArtboard(String popCode);
	
	@Insert("INSERT INTO artboard (POP_CODE, POP_NAME, START_DAY, END_DAY, content, weblink, rink, place)"
			+ "VALUES (#{popCode}, #{popName}, #{startDay} , #{endDay}, #{content}, #{weblink}, #{rink}, #{place} )")       
	void insertArtboard(Artboard artboard);

	@Update("UPDATE artboard set POP_NAME= #{popName}, START_DAY= #{startDay}, END_DAY= #{endDay}, weblink=#{weblink} content=#{content}, img= #{img}, rink= #{rink}, place= #{place}"
			           +"where POP_CODE= #{popCode}")
	void updateArtboard();
	
	@Delete("delete from artboard where POP_CODE= #{popCode}")
    void deleteArtboard(String artCode);
	
}
