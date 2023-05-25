import React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { useState, useEffect } from 'react';
import styled from 'styled-components';
import { CvContentAtom } from '../../recoil/CvContentAtom';
import { useRecoilState } from 'recoil';
import TagInput from './TagInput';

const days = [
  '일',
  '1',
  '2',
  '3',
  '4',
  '5',
  '6',
  '7',
  '8',
  '9',
  '10',
  '11',
  '12',
  '13',
  '14',
  '15',
  '16',
  '17',
  '18',
  '19',
  '20',
  '21',
  '22',
  '23',
  '24',
  '25',
  '26',
  '27',
  '28',
  '29',
  '30',
  '31',
];

const months = [
  '월',
  '1',
  '2',
  '3',
  '4',
  '5',
  '6',
  '7',
  '8',
  '9',
  '10',
  '11',
  '12',
];
const years = [
  '년',
  '2023',
  '2022',
  '2021',
  '2020',
  '2019',
  '2018',
  '2017',
  '2016',
  '2015',
  '2014',
  '2013',
  '2012',
  '2011',
  '2010',
  '2009',
  '2008',
  '2007',
  '2006',
  '2005',
  '2004',
  '2003',
  '2002',
  '2001',
  '2000',
  '1999',
  '1998',
  '1997',
  '1996',
  '1995',
  '1994',
  '1993',
  '1992',
  '1991',
  '1990',
  '1989',
  '1988',
  '1987',
  '1986',
  '1985',
  '1984',
  '1983',
  '1982',
  '1981',
  '1980',
  '1979',
  '1978',
  '1977',
  '1976',
  '1975',
  '1974',
  '1973',
  '1972',
  '1971',
  '1970',
  '1969',
  '1968',
  '1967',
  '1966',
  '1965',
  '1964',
  '1963',
  '1962',
  '1961',
  '1960',
];
const jobs = [
  '직무',
  '서버/백엔드 개발자',
  '프론트엔드 개발자',
  '웹 풀스택 개발자',
  '안드로이드 개발자',
  'IOS 개발자',
  '블록체인',
  '개발 PM',
];
const CvBasicInfo = ({ check, setCheck }) => {
  const [tags, setTags] = useState('');
  const [title, setTitle] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [birthMonth, setBirthMonth] = useState('');
  const [birthDay, setBirthDay] = useState('');
  const [birthYear, setBirthYear] = useState('');
  const [developmentJob, setDevelopmentJob] = useState('');
  const [selfIntroduction, setSelfIntroduction] = useState('');
  const [url1, setUrl1] = useState('');
  const [url2, setUrl2] = useState('');
  const [url3, setUrl3] = useState('');
  const [url4, setUrl4] = useState('');
  const [cvContent, setCvContent] = useRecoilState(CvContentAtom);
  const user = localStorage.getItem('user_info');
  const { userId } = JSON.parse(user);
  const [isEmpty, setIsEmpty] = useState(false);

  // const [inputs, setInputs] = useState({
  //   title: '',
  //   name: '',
  //   email: '',
  //   phone: '',
  //   address: '',
  //   birthMonth: '',
  //   birthDay: '',
  //   birthYear: '',
  //   developmentJob: '',
  //   selfIntroduction: '',
  //   cvSkillStacks: '',
  //   url1: '',
  //   url2: '',
  //   url3: '',
  //   url4: '',
  // });

  // const {
  //   title,
  //   name,
  //   email,
  //   phone,
  //   address,
  //   birthMonth,
  //   birthDay,
  //   birthYear,
  //   developmentJob,
  //   selfIntroduction,
  //   cvSkillStacks,
  //   url1,
  //   url2,
  //   url3,
  //   url4,
  // } = inputs;
  // const onChange = (e) => {
  //   const { value, name } = e.target;
  //   setInputs({
  //     ...inputs,
  //     [name]: value,
  //   });
  // };

  const handleClickSave = () => {
    if (title !== '' && name !== '') {
      setIsEmpty(false);
      alert('임시저장이 완료되었습니다.');
      setCvContent((prev) => ({ ...prev, ...cvContent1 }));
      setCheck(true);
    } else {
      setIsEmpty(true);
    }
    //비동기 해결해야함.
  };

  useEffect(() => {
    localStorage.setItem('CvBasicContent', JSON.stringify({ ...cvContent }));
  }, [cvContent]);
  // const getData = JSON.parse(localStorage.getItem('CvBasicContent'));
  console.log('c', check);
  useEffect(() => {
    if (cvContent) {
      const CvBasicContent = localStorage.getItem('CvBasicContent');
      const {
        title,
        name,
        email,
        phone,
        address,
        birthMonth,
        birthDay,
        birthYear,
        developmentJob,
        selfIntroduction,
        links,
      } = JSON.parse(CvBasicContent);
      setTitle(title);
      setName(name);
      setEmail(email);
      setPhone(phone);
      setAddress(address);
      setBirthMonth(birthMonth);
      setBirthDay(birthDay);
      setBirthYear(birthYear);
      setDevelopmentJob(developmentJob);
      setSelfIntroduction(selfIntroduction);
      setUrl1(links[0].linkAddress);
      setUrl2(links[1].linkAddress);
      setUrl3(links[2].linkAddress);
      setUrl4(links[3].linkAddress);
    }
  }, []);

  const cvContent1 = {
    userId: userId,
    title: title,
    name: name,
    email: email,
    phone: phone,
    address: address,
    birthMonth: birthMonth,
    birthDay: birthDay,
    birthYear: birthYear,
    developmentJob: developmentJob,
    selfIntroduction: selfIntroduction,
    // cvSkillStacks: [
    //   {
    //     skillStackId: tags,
    //   },
    // ],
    links: [
      {
        linkName: 'LINK_GITHUB',
        linkAddress: url1,
      },
      {
        linkName: 'LINK_NOTION',
        linkAddress: url2,
      },
      {
        linkName: 'LINK_BLOG',
        linkAddress: url3,
      },
      {
        linkName: 'LINK_PORTFOLIO',
        linkAddress: url4,
      },
    ],
    educations: [
      {
        degree: '',
        major: '',
        schoolName: '',
        admissionMonth: '',
        admissionYear: '',
        graduationMonth: '',
        graduationYear: '',
        description: '',
      },
    ],

    careers: [
      {
        companyName: '',
        duty: '',
        developmentJob: '',
        joinMonth: '',
        joinYear: '',
        retirementMonth: '',
        retirementYear: '',
        description: '',
      },
    ],

    projects: [
      {
        projectSubject: '',
        part: '',
        link: '',
        startMonth: '',
        startYear: '',
        endMonth: '',
        endYear: '',
        description: '',
      },
    ],
  };

  const onChange = (event) => {
    const {
      target: { name, value },
    } = event;
    if (name === 'birthday') {
      setBirthDay(value);
    } else if (name === 'birthmonth') {
      setBirthMonth(value);
    } else if (name === 'birthyear') {
      setBirthYear(value);
    } else if (name === 'developmentjob') {
      setDevelopmentJob(value);
    } else if (name === 'title') {
      setTitle(value);
    } else if (name === 'name') {
      setName(value);
    } else if (name === 'email') {
      setEmail(value);
    } else if (name === 'phone') {
      setPhone(value);
    } else if (name === 'address') {
      setAddress(value);
    } else if (name === 'selfintroduction') {
      setSelfIntroduction(value);
    } else if (name === 'tags') {
      setTags(value);
    } else if (name === 'giturl') {
      setUrl1(value);
    } else if (name === 'notionurl') {
      setUrl2(value);
    } else if (name === 'blogurl') {
      setUrl3(value);
    } else if (name === 'ptpurl') {
      setUrl4(value);
    }
  };
  return (
    <Container>
      <div className="body">
        <div className="title">
          {isEmpty && <Warning>이력서 제목을 입력하세요.</Warning>}
          <input
            maxLength={20}
            name="title"
            type="text"
            value={title}
            onChange={onChange}
            placeholder="* 이력서 제목을 입력하세요."
          ></input>
          <hr></hr>
        </div>
        <div className="test2">
          <div className="photo">
            <img src="https://blog.kakaocdn.net/dn/OZ3vp/btqWW9GQeUf/AscsDSgZbtKRKXxMuw2bPk/img.jpg" />
          </div>
          <div className="name">
            {isEmpty && <Warning>성명을 입력하세요.</Warning>}
            <span>성명</span>
            <input
              name="name"
              type="text"
              value={name}
              onChange={onChange}
              placeholder="* 성명을 입력해주세요."
              maxLength={10}
            ></input>
            <span>이메일 주소</span>
            <input
              name="email"
              type="text"
              value={email}
              onChange={onChange}
              maxLength={30}
            ></input>
            <span>전화번호</span>
            <input
              name="phone"
              type="text"
              value={phone}
              placeholder="하이픈(-)을 포함한 형식으로 입력해주세요. ex) 010-1111-1111"
              onChange={onChange}
              maxLength={13}
            ></input>
            <span>주소</span>
            <input
              name="address"
              type="text"
              value={address}
              onChange={onChange}
              maxLength={50}
            ></input>
          </div>
        </div>
        <div className="test2">
          <div className="birth2">
            <span>생년월일</span>
            <FormControl sx={{ m: 1, width: 115, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="birthmonth"
                value={birthMonth}
                label="Month"
                onChange={onChange}
              >
                {months.map((month) => (
                  <MenuItem key={month} value={month}>
                    {month}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
            <FormControl sx={{ m: 1, width: 120 }}>
              <InputLabel id="demo-simple-select-label">일</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="birthday"
                value={birthDay}
                label="Day"
                onChange={onChange}
              >
                {days.map((day) => (
                  <MenuItem key={day} value={day}>
                    {day}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
            <FormControl sx={{ m: 1, width: 170 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="birthyear"
                value={birthYear}
                label="Year"
                onChange={onChange}
              >
                {years.map((year) => (
                  <MenuItem key={year} value={year}>
                    {year}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </div>
          <div className="develop">
            <span>개발 직무</span>

            <FormControl sx={{ m: 1, width: 190 }}>
              <InputLabel id="demo-simple-select-label">직무</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="developmentjob"
                value={developmentJob}
                label="job"
                onChange={onChange}
              >
                {jobs.map((job) => (
                  <MenuItem key={job} value={job}>
                    {job}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </div>
        </div>

        <div className="intro">
          <span>자기소개</span>
          <textarea
            name="selfintroduction"
            type="text"
            value={selfIntroduction}
            onChange={onChange}
          ></textarea>
        </div>

        <div className="tag">
          <span>기술스택 태그</span>
          <TagInput tags={tags} setTags={setTags} />
        </div>
        <div className="link">
          <div>
            <span>링크</span>
          </div>
          <div>
            <span>
              <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_github.png" />
              Git hub
            </span>
          </div>
          <input
            name="giturl"
            type="text"
            value={url1}
            placeholder="URL을 입력해주세요."
            onChange={onChange}
          ></input>

          <div>
            <span>
              <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_notion.png" />
              Notion
            </span>
          </div>
          <input
            name="notionurl"
            type="text"
            value={url2}
            placeholder="URL을 입력해주세요."
            onChange={onChange}
          ></input>

          <div>
            <span>
              <img src="https://cdn.jumpit.co.kr/jumpit/personal/img_blog.png" />
              Blog
            </span>
          </div>
          <input
            name="blogurl"
            type="text"
            value={url3}
            placeholder="URL을 입력해주세요."
            onChange={onChange}
          ></input>

          <div>
            <span>
              <img src="https://cdn.icon-icons.com/icons2/2568/PNG/512/link_icon_153723.png" />
              포트폴리오
            </span>
          </div>
          <input
            name="ptpurl"
            type="text"
            value={url4}
            placeholder="URL을 입력해주세요."
            onChange={onChange}
          ></input>
        </div>
        <StyledButton onClick={handleClickSave}>임시저장</StyledButton>
      </div>
    </Container>
  );
};

export default CvBasicInfo;

const Container = styled.div`
  margin: 0rem 4rem 8rem 4rem;
  display: flex;
  flex-direction: column;

  width: 55rem;
  background-color: white;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  input {
    width: 100%;
    height: 2.5rem;
    margin: 0 0 1rem 0rem;
    border-radius: 0.2rem;
    border: 1px solid #c8c8c8;

    :hover {
      border: 1px solid black;
    }
  }
  span {
    font-size: 1rem;
    font-weight: bold;
    color: gray;
  }

  img {
    width: 16rem;
  }

  .name {
    display: flex;
    flex-direction: column;
    width: 41rem;
  }
  .test2 {
    display: flex;
  }
  .photo {
    width: 20rem;
    height: 20rem;
    margin: 0 1rem 0 0rem;
  }
  .body {
    margin: 3rem;
  }
  .birth2 {
    width: 32.2rem;
  }
  .develop {
    width: 16.8rem;
  }

  .intro {
    margin: 1rem 0 0 0;
    textarea {
      width: 100%;
      height: 20rem;
      border-radius: 0.2rem;
      border: 1px solid #c8c8c8;
      :hover {
        border: 1px solid black;
      }
    }
  }
  .link {
    margin-top: 2rem;
    img {
      margin-right: 0.5rem;
      width: 1.2rem;
      height: 1.2rem;
    }
  }
  .port {
    width: 20rem;
  }
  .title {
    input {
      width: 16rem;
      margin: 0;
    }
  }
  .tag {
    margin-top: 1rem;
  }
`;

const Warning = styled.p`
  color: red;
  font-size: 0.8rem;
  font-weight: 800;
`;

const StyledButton = styled.button`
  width: 100%;
  height: 3.5rem;
  font-size: 0.8rem;
  font-weight: bold;
  cursor: pointer;
  padding: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: var(--puple100);
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

  &:hover {
    background-color: var(--puple300);
  }
`;
