import React from 'react';
import styled from 'styled-components';

const CvSample = () => {
  return (
    <Container>
      <div className="test1">
        <div className="photo">
          <img src="https://blog.kakaocdn.net/dn/OZ3vp/btqWW9GQeUf/AscsDSgZbtKRKXxMuw2bPk/img.jpg" />
        </div>
        <div>
          <h4>기본 정보</h4>
        </div>
        <hr></hr>
        <div>
          <h4>성명</h4>
          <div>
            <span>홍길동</span>
          </div>
        </div>
        <div>
          <h4>이메일</h4>
          <div>
            <span>example@naver.com</span>
          </div>
        </div>
        <div>
          <h4>전화번호</h4>
          <div>
            <span>010-1234-1234</span>
          </div>
        </div>
        <div>
          <h4>주소</h4>
          <div>
            <span>서울특별시 강남구</span>
          </div>
        </div>
        <div>
          <h4>생년월일</h4>
          <div>
            <span>1995년 1월 1일</span>
          </div>
        </div>

        <div>
          <h4>개발직무</h4>

          <div>
            <span>서버/백엔드 개발자</span>
          </div>
        </div>
      </div>
      <div className="test2">
        <div>
          <h3>열심히 하는 인재입니다</h3>
        </div>
        <hr></hr>
        <div>
          태어날때 부터 개발자의 운명이었던것 같습니다. 귀사에 근무 시 최선을
          다하여 노력하겠습니다. 저를 뽑아주신다면 항상 발전하는 모습을
          보여드리도록 하겠습니다.
        </div>
        <h4>학력 및 자격</h4>
        <hr></hr>
        <div className="edu">
          <div>
            <span>학사 졸업</span>
          </div>
          <div>
            <span>
              2010년 {}
              3월- 2012년 {}
              5월
            </span>
          </div>
        </div>
        <div>서울대학교</div>
        <div>컴퓨터공학</div>
        <div>한국 최고의 대학 서울대학교에서 4년 공부 후 졸업했습니다</div>
        <h4>경력</h4>
        <hr></hr>
        <div className="edu">
          <div>
            <span>팀장</span>
          </div>
          <div>
            <span>
              2010년 {}
              3월- 2012년 {}
              5월
            </span>
          </div>
        </div>
        <div>카카오엔터테인먼트</div>
        <div>서버/백엔드 개발자</div>
        <div>5년 재직 후 퇴사 하였으며 최고의 IT 기업입니다.</div>
        <h4>프로젝트</h4>
        <hr></hr>
        <div className="edu">
          <div>
            <span>로켓CV</span>
          </div>
          <div>
            <span>
              2010년 {}
              3월- 2012년 {}
              5월
            </span>
          </div>
        </div>
        <div>팀장</div>
        <div>http://www.rocketCV.com</div>
        <div>
          신입 개발자의 이력서를 로켓처럼 빠르게 제작해주는 서비스를
          제작하였습니다.
        </div>

        <h4>사용자 정의 섹션</h4>
        <h4>링크</h4>
        <div>
          <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_github.png" />
          GITHUB : http://github.com
        </div>
        <div>
          <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_notion.png" />
          NOTION : http://notion.com
        </div>
        <div>
          <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_blog.png" />
          BLOG : http://blog.com
        </div>
        <div>
          <img src="https://cdn.icon-icons.com/icons2/2568/PNG/512/link_icon_153723.png" />
          PORTFOLIO : http://rocketCV.com
        </div>
      </div>
    </Container>
  );
};

export default CvSample;

const Container = styled.div`
  margin: 0 4rem 8rem 4rem;
  display: flex;
  flex-direction: row;

  width: 55rem;
  background-color: white;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

  .test1 {
    width: 20%;
    height: 67rem;

    background-color: var(--puple300);
    span {
      margin: 1rem;
      font-size: 1rem;
    }
    h4 {
      margin-left: 1rem;
    }
  }
  .test2 {
    margin: 1rem;
    width: 80%;
    height: 65rem;
  }
  .photo {
    img {
      margin: 1rem;
      width: 9rem;
      height: 9rem;
      border-radius: 5rem;
    }
  }
  .edu {
    display: flex;
    justify-content: space-between;
    span {
      font-size: 1rem;
    }
  }
  hr {
    margin-left: 1rem;
    margin-bottom: 1rem;
  }
  h4 {
    margin-top: 1rem;
  }
  img {
    margin-right: 0.5rem;
    width: 1.2rem;
    height: 1.2rem;
  }
`;
