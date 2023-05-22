import React from 'react';
import styled from 'styled-components';

const UserCv = () => {
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
            <span>{res.name}</span>
          </div>
        </div>
        <div>
          <h4>이메일</h4>
          <div>
            <span>{res.email}</span>
          </div>
        </div>
        <div>
          <h4>전화번호</h4>
          <div>
            <span>{res.phone}</span>
          </div>
        </div>
        <div>
          <h4>주소</h4>
          <div>
            <span>{res.address}</span>
          </div>
        </div>
        <div>
          <h4>생년월일</h4>
          <div>
            <div>
              <span>
                {rest.birthYear}년-{res.birthMonth}월-
                {res.birthDay}일
              </span>
            </div>
          </div>
        </div>

        <div>
          <h4>개발직무</h4>

          <div>
            <span>{res.developmentJob}</span>
          </div>
        </div>
      </div>
      <div className="test2">
        <div>
          <h3>{res.title}</h3>
        </div>
        <hr></hr>
        <div>{res.selfIntroduction}</div>
        <h4>학력 및 자격</h4>
        <hr></hr>
        <div className="edu">
          <div>
            <span>{res.educations[0].degree}</span>
          </div>
          <div>
            <span>
              {res.educations[0].admissionYear}년 {}
              {res.educations[0].admissionMonth}월-
              {res.educations[0].graduationYear}년 {}
              {res.educations[0].graduationMonth}월
            </span>
          </div>
        </div>
        <div>{res.educations[0].schoolName}</div>
        <div>{res.educations[0].major}</div>
        <div>{res.educations[0].description}</div>
        <h4>경력</h4>
        <hr></hr>
        <div className="edu">
          <div>{res.careers[0].duty}</div>
          <div>
            <span>
              {res.careers[0].joinYear}년 {}
              {res.careers[0].joinMonth}월-
              {res.careers[0].retirementYear}년 {}
              {res.careers[0].retirementMonth}월
            </span>
          </div>
        </div>
        <div>{res.careers[0].companyName}</div>
        <div>{res.careers[0].developmentJob}</div>
        <div>{res.careers[0].description}</div>
        <h4>프로젝트</h4>
        <hr></hr>
        <div className="edu">
          <div>{res.projects[0].projectSubject}</div>
          <div>
            <span>
              {res.projects[0].startYear}년 {}
              {res.projects[0].startMonth}월-
              {res.projects[0].endYear}년 {}
              {res.projects[0].endMonth}월
            </span>
          </div>
        </div>
        <div>{res.projects[0].part}</div>
        <div>{res.projects[0].link}</div>
        <div>{res.projects[0].description}</div>

        <h4>사용자 정의 섹션</h4>
        <h4>링크</h4>
        <div>
          <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_github.png" />
          GITHUB : {res.links[0].linkAddress}
        </div>
        <div>
          <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_notion.png" />
          NOTION : {rest.links[1].linkAddress}
        </div>
        <div>
          <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_blog.png" />
          BLOG : {res.links[2].linkAddress}
        </div>
        <div>
          <img src="https://cdn.icon-icons.com/icons2/2568/PNG/512/link_icon_153723.png" />
          PORTFOLIO : {res.links[3].linkAddress}
        </div>
      </div>
    </Container>
  );
};

export default UserCv;

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
