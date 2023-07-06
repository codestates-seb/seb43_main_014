import React from 'react';
import styled from 'styled-components';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { API } from '../../utils/API';

const UserCv = () => {
  const token = localStorage.getItem('jwt_token');
  const user = localStorage.getItem('user_info');
  const { cvId } = JSON.parse(user);

  const [userCvData, setUserCvData] = useState('');

  console.log('cvId', cvId);
  useEffect(() => {
    axios
      .get(
        `${API}/cv/${cvId}`,
        //요청할 api 주소, api명세를 보고 작성(path)
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
        },
      )
      .then((res) => {
        setUserCvData(res.data);
        console.log('userdata', res.data);
      })
      .catch((ex) => {
        //오류가 발생했을때 오류를 콘솔에 찍는 것
        console.log(ex);
        alert('서버가 정상적이지 않음.');
      });
  }, [cvId]);
  console.log('userdata', userCvData);
  if (userCvData) {
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
              <span>{userCvData.name}</span>
            </div>
          </div>
          <div>
            <h4>이메일</h4>
            <div>
              <span>{userCvData.email}</span>
            </div>
          </div>
          <div>
            <h4>전화번호</h4>
            <div>
              <span>{userCvData.phone}</span>
            </div>
          </div>
          <div>
            <h4>주소</h4>
            <div>
              <span>{userCvData.address}</span>
            </div>
          </div>
          <div>
            <h4>생년월일</h4>
            <div>
              <div>
                <span>
                  {userCvData.birthYear}년-{userCvData.birthMonth}월-
                  {userCvData.birthDay}일
                </span>
              </div>
            </div>
          </div>

          <div>
            <h4>개발직무</h4>
            <div>
              <span>{userCvData.developmentJob}</span>
            </div>
          </div>
          <div>
            <h4>기술스택</h4>
            <div>
              {/* <span>
                {userCvData.cvSkillStacks[0].skillStackId[0]} {}
                {userCvData.cvSkillStacks[0].skillStackId[1]} {}
                {userCvData.cvSkillStacks[0].skillStackId[2]} {}
              </span> */}
            </div>
          </div>
        </div>
        <div className="test2">
          <div>
            <h3>{userCvData.title}</h3>
          </div>
          <hr></hr>
          <div>{userCvData.selfIntroduction}</div>
          <h4>학력 및 자격</h4>
          <hr></hr>
          <div className="edu">
            <div>
              <span>{userCvData.educations[0].degree}</span>
            </div>
            <div>
              <span>
                {userCvData.educations[0].admissionYear}년 {}
                {userCvData.educations[0].admissionMonth}월-
                {userCvData.educations[0].graduationYear}년 {}
                {userCvData.educations[0].graduationMonth}월
              </span>
            </div>
          </div>
          <div>{userCvData.educations[0].schoolName}</div>
          <div>{userCvData.educations[0].major}</div>
          <div>{userCvData.educations[0].description}</div>
          <h4>경력</h4>
          <hr></hr>
          <div className="edu">
            <div>{userCvData.careers[0].duty}</div>
            <div>
              <span>
                {userCvData.careers[0].joinYear}년 {}
                {userCvData.careers[0].joinMonth}월-
                {userCvData.careers[0].retirementYear}년 {}
                {userCvData.careers[0].retirementMonth}월
              </span>
            </div>
          </div>
          <div>{userCvData.careers[0].companyName}</div>
          <div>{userCvData.careers[0].developmentJob}</div>
          <div>{userCvData.careers[0].description}</div>
          <h4>프로젝트</h4>
          <hr></hr>
          <div className="edu">
            <div>{userCvData.projects[0].projectSubject}</div>
            <div>
              <span>
                {userCvData.projects[0].startYear}년 {}
                {userCvData.projects[0].startMonth}월-
                {userCvData.projects[0].endYear}년 {}
                {userCvData.projects[0].endMonth}월
              </span>
            </div>
          </div>
          <div>{userCvData.projects[0].part}</div>
          <div>{userCvData.projects[0].link}</div>
          {/* <div>
            {userCvData.projects[0].projectSkillStacks[0].skillStackId[0]} {}
            {userCvData.projects[0].projectSkillStacks[0].skillStackId[1]} {}
            {userCvData.projects[0].projectSkillStacks[0].skillStackId[2]} {}
          </div> */}
          <div>{userCvData.projects[0].description}</div>
          <h4>사용자 정의 섹션</h4>
          <h4>링크</h4>
          <div>
            <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_github.png" />
            GITHUB : {userCvData.links[0].linkAddress}
          </div>
          <div>
            <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_notion.png" />
            NOTION : {userCvData.links[1].linkAddress}
          </div>
          <div>
            <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_blog.png" />
            BLOG : {userCvData.links[2].linkAddress}
          </div>
          <div>
            <img src="https://cdn.icon-icons.com/icons2/2568/PNG/512/link_icon_153723.png" />
            PORTFOLIO : {userCvData.links[3].linkAddress}
          </div>
        </div>
      </Container>
    );
  }
};

export default UserCv;

const Container = styled.div`
  display: flex;
  flex-direction: row;
  margin: 5rem auto;
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
