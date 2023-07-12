package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//게시글 목록 조회 메소드
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="currentPage", defaultValue="1")int currentPage,Model model) {
		
		//게시글 목록 조회해오기
		int listCount = boardService.selectListCount();
		int pageLimit = 10;
		int boardLimit = 5;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Board> bList = boardService.selectList(pi);
		
		model.addAttribute("bList",bList);
		model.addAttribute("pi", pi);
		
		return "board/boardListPage";
	}
	
	//게시글 상세 조회 메소드
	@RequestMapping("detail.bo")
	public ModelAndView selectBoard(int bno,ModelAndView mv) {
		
		int result = boardService.increaseCount(bno);
		
		if(result>0) {
			Board b = boardService.selectBoard(bno);
			ArrayList<Reply> rList = boardService.selectReply(bno);
			if(b!=null) {
				mv.addObject("rList",rList).addObject("b",b).setViewName("board/boardDetailPage");
			}else {
				mv.addObject("errorMsg","게시글 조회 실패").setViewName("common/errorPage");
			}
		}else {
			mv.addObject("errorMsg","게시글 조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	//글쓰기 페이지
	@RequestMapping("enrollForm.bo")
	public String enrollFrom() {
		return "board/boardEnrollForm";
	}
	
	//글작성 메소드
	@RequestMapping("insert.bo")
	public ModelAndView insertBoard(ModelAndView mv,Board b,MultipartFile upfile,HttpSession session) {
		
		//첨부파일을 지정하던 안하던 이미 객체는 만들어져서 넘어온다.(filename에 원본명을 찾아서 있는지 없는지로 판별)
		//전달된 파일이 있을 경우 - 파일명 수정작업 후 서버로 업로드 -> 원본명,서버에 업로드된 파일명 경로를 b에 추가 하여 전달
		if(!upfile.getOriginalFilename().equals("")) {
			//파일명 수정 작업 후 서버에 업로드
			//예) dog.jpg -> 20230516160533123512.jsp
//			//1.원본 파일명 뽑기
//			String originName = upfile.getOriginalFilename();
//			
//			//2.시간형식 문자열로 뽑아내기
//			//202305163033
//			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//			
//			//3.뒤에 붙을 5자리 랜덤값 뽑아주기
//			int ranNum = (int)(Math.random()*90000+10000); //5자리 랜덤값
//			
//			//4.확장자명 추출하기
//			String ext = originName.substring(originName.lastIndexOf("."));
//			
//			//5.추출한 문자열들 다 합쳐서 changeName 만들기
//			String changeName = currentTime + ranNum + ext;
//			
//			//6.업로드하고자 하는 물리적인 경로 알아내기
//			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
//			
//			//7.경로와 수정파일명을 합쳐 파일 업로드 하기
//			try {
//				upfile.transferTo(new File(savePath+changeName));
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			String changeName = saveFile(upfile,session);
			String originName = upfile.getOriginalFilename();
			b.setOriginName(originName);
			b.setChangeName("resources/uploadFiles/"+changeName);
		}
		
		int result = boardService.insertBoard(b);
		if(result>0) {
			mv.addObject("alertMsg","게시글 작성 성공");
			mv.setViewName("redirect:list.bo");
		}else {
			mv.addObject("errorMsg","게시글 작성 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	//게시글 수정 페이지 메소드
	@RequestMapping("updateForm.bo")
	public String updateForm(int bno,Model model) {
		
		Board b = boardService.selectBoard(bno);
		model.addAttribute("b",b);
		
		return "board/boardUpdateForm";
	}
	
	//게시글 수정 메소드
 	@RequestMapping(value="updateBoard.bo",method=RequestMethod.POST)
//	@GetMapping()
//	@PostMapping()
	public ModelAndView updateBoard(Board b, ModelAndView mv, MultipartFile upfile,HttpSession session) {
		
 		//새로운 첨부파일이 넘어왔을 경우
		if(!upfile.getOriginalFilename().equals("")) {
			//새로운 첨부파일이 있는 경우에는 기존의 첨부파일 찾아서 삭제
			if(b.getOriginName()!=null) {
				new File(session.getServletContext().getRealPath(b.getChangeName())).delete();
			}
			//새로 넘어온 첨부파일을 서벌에 업로드 시키기
			String changeName = saveFile(upfile,session);
			String originName = upfile.getOriginalFilename();
			//처리된 변경이름, 넘어온 실제이름 b에 담아서 요청보내기
			b.setOriginName(originName);
			b.setChangeName("/resources/uploadFiles/"+changeName);
		}
		/*
		 * b에는 boardNo,boardTitle,boardContent가 담겨있고
		 *	
		 * 추가적으로 고려해야하는 경우는
		 * 1.새로 첨부된 파일이 없고 기존파일도 없을때
		 * 2.새로 첨부된 파일이 없고 기존파일은 있을때
		 * 3.새로 첨부된 파일이 있고 기존파일은 없을때
		 * -새로 전달된 파일을 서버에 저장하고 데이터베이스에도 등록
		 * 4.새로 첨부된 파일이 있고 기존 파일도 있을때
		 * -기존 파일을 삭제하고
		 * -새로 첨부된 파일을 저장 및 등록
		 * */
		
		//Service로 전달
		int result = boardService.updateBoard(b);
		
		if(result>0) {
			//상세페이지로 이동
			session.setAttribute("alertMsg", "게시글 수정 완료");
			mv.setViewName("redirect:detail.bo?bno"+b.getBoardNo());
		}else {
			//수정실패 에러페이지 이동
			mv.addObject("errorMsg", "게시글 수정 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
 	@RequestMapping("delete.bo")
 	public ModelAndView deleteBoard(int boardNo,String filePath,ModelAndView mv, HttpSession session) {
 		
 		int result = boardService.deleteBoard(boardNo);
 		
 		if(result>0) {
 			if(!filePath.equals("")) {//넘어온 파일정보가 있을때
 				new File(session.getServletContext().getRealPath(filePath)).delete();
 			}
 			session.setAttribute("alertMsg", "게시글 삭제 완료");
 			mv.setViewName("redirect:list.bo");
 		}else {
 			mv.addObject("errorMsg","게시글 삭제 실패").setViewName("common.errorPage");
 		}
 		return mv;
 	}
 	
 	@ResponseBody
 	@RequestMapping("insertReply.bo")
 	public String insertReply(Reply r) {
 		
 		int result = boardService.insertReply(r);
 		
 		return (result>0)?"success":"fail";
 	}
 	
 	@ResponseBody
 	@RequestMapping(value="toplist.bo",produces="application/json;charset=UTF-8")
 	public String selectTopList() {
 		
 		//보여질 순위
 		int rank = 5;
 		ArrayList<Board> bList = boardService.selectTopList(rank);
 		
 		return new Gson().toJson(bList);
 	}
 	
	//파일 업로드 처리 메소드(모듈)
	public String saveFile(MultipartFile upfile,HttpSession session) {
		//1.원본 파일명 뽑기
		String originName = upfile.getOriginalFilename();
		
		//2.시간형식 문자열로 뽑아내기
		//202305163033
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		//3.뒤에 붙을 5자리 랜덤값 뽑아주기
		int ranNum = (int)(Math.random()*90000+10000); //5자리 랜덤값
		
		//4.확장자명 추출하기
		String ext = originName.substring(originName.lastIndexOf("."));
		
		//5.추출한 문자열들 다 합쳐서 changeName 만들기
		String changeName = currentTime + ranNum + ext;
		
		//6.업로드하고자 하는 물리적인 경로 알아내기
		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
		
		//7.경로와 수정파일명을 합쳐 파일 업로드 하기
		try {
			upfile.transferTo(new File(savePath+changeName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return changeName;
	}
}
