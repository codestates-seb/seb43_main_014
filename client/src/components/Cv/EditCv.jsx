import React from 'react';
import styled from 'styled-components';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { API } from '../../utils/API';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { CvContentAtom } from '../../recoil/CvContentAtom';
import TagInput from './TagInput';
import { useRecoilState } from 'recoil';
import { useNavigate } from 'react-router-dom';

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
const EditCv = () => {
  const navigate = useNavigate();
  const [cvContent, setCvContent] = useRecoilState(CvContentAtom);

  const token = localStorage.getItem('jwt_token');
  const user = localStorage.getItem('user_info');
  const { cvId } = JSON.parse(user);
  const { userId } = JSON.parse(user);
  console.log('cvId', cvId);
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
  const [isEmpty, setIsEmpty] = useState(false);

  const [degree, setDegree] = useState('');
  const [major, setMajor] = useState('');
  const [schoolName, setSchoolName] = useState('');
  const [admissionMonth, setAdmissionMonth] = useState('');
  const [admissionYear, setAdmissionYear] = useState('');
  const [graduationMonth, setGraduationMonth] = useState('');
  const [graduationYear, setGraduationYear] = useState('');
  const [eduDescription, setEduDescription] = useState('');

  const [companyName, setCompanyName] = useState('');
  const [duty, setDuty] = useState('');
  const [careersDevelopmentJob, setCareersDevelopmentJob] = useState('');
  const [joinMonth, setJoinMonth] = useState('');
  const [joinYear, setJoinYear] = useState('');
  const [retirementMonth, setRetirementMonth] = useState('');
  const [retirementYear, setRetirementYear] = useState('');
  const [jobDescription, setJobDescription] = useState('');

  const [projectSubject, setProjectSubject] = useState('');
  const [part, setPart] = useState('');
  const [link, setLink] = useState('');
  const [startMonth, setStartMonth] = useState('');
  const [startYear, setStartYear] = useState('');
  const [endMonth, setEndMonth] = useState('');
  const [endYear, setEndYear] = useState('');
  const [proDescription, setProDescription] = useState('');

  const [tags, setTags] = useState('');

  const [userCvData, setUserCvData] = useState('');

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
        setTitle(res.data.title);
        setName(res.data.name);
        setEmail(res.data.email);
        setPhone(res.data.phone);
        setAddress(res.data.address);
        setBirthMonth(res.data.birthMonth);
        setBirthDay(res.data.birthDay);
        setBirthYear(res.data.birthYear);
        setDevelopmentJob(res.data.developmentJob);
        setSelfIntroduction(res.data.selfIntroduction);
        setUrl1(res.data.links[0].linkAddress);
        setUrl2(res.data.links[1].linkAddress);
        setUrl3(res.data.links[2].linkAddress);
        setUrl4(res.data.links[3].linkAddress);
        setDegree(res.data.educations[0].degree);
        setMajor(res.data.educations[0].major);
        setSchoolName(res.data.educations[0].schoolName);
        setAdmissionMonth(res.data.educations[0].admissionMonth);
        setAdmissionYear(res.data.educations[0].admissionYear);
        setGraduationMonth(res.data.educations[0].graduationMonth);
        setGraduationYear(res.data.educations[0].graduationYear);
        setEduDescription(res.data.educations[0].description);
        setCompanyName(res.data.careers[0].companyName);
        setDuty(res.data.careers[0].duty);
        setCareersDevelopmentJob(res.data.careers[0].developmentJob);
        setJoinMonth(res.data.careers[0].joinMonth);
        setJoinYear(res.data.careers[0].joinYear);
        setRetirementMonth(res.data.careers[0].retirementMonth);
        setRetirementYear(res.data.careers[0].retirementYear);
        setJobDescription(res.data.careers[0].description);
        setProjectSubject(res.data.projects[0].projectSubject);
        setPart(res.data.projects[0].part);
        setLink(res.data.projects[0].link);
        setStartMonth(res.data.projects[0].startMonth);
        setStartYear(res.data.projects[0].startYear);
        setEndMonth(res.data.projects[0].endMonth);
        setEndYear(res.data.projects[0].endYear);
        setProDescription(res.data.projects[0].description);
        setTags(res.data.tags);
        setUserCvData(res.data);
        console.log('userdata', res.data);
      })
      .catch((ex) => {
        //오류가 발생했을때 오류를 콘솔에 찍는 것
        console.log(ex);
        alert('서버가 정상적이지 않음.');
      });
  }, [cvId]);
  console.log('나와라좀', cvContent);
  const handleClickSave = () => {
    if (title !== '' && name !== '') {
      setIsEmpty(false);
      alert('이력서 수정이 완료되었습니다.');
      setCvContent(() => ({ ...cvContent1 }));
      axios
        .patch(`${API}/cv/edit/${cvId}`, cvContent, {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
        })
        .then((res) => {
          console.log(res);
          navigate('/mypage');
        })
        .catch((error) => {
          console.log(error);
          alert('이력서 수정이 실패하였습니다.');
        });
    } else {
      setIsEmpty(true);
    }
    //비동기 해결해야함.
  };

  const cvContent1 = {
    cvId: cvId,
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
        degree: degree,
        major: major,
        schoolName: schoolName,
        admissionMonth: admissionMonth,
        admissionYear: admissionYear,
        graduationMonth: graduationMonth,
        graduationYear: graduationYear,
        description: eduDescription,
      },
    ],

    careers: [
      {
        companyName: companyName,
        duty: duty,
        developmentJob: careersDevelopmentJob,
        joinMonth: joinMonth,
        joinYear: joinYear,
        retirementMonth: retirementMonth,
        retirementYear: retirementYear,
        description: jobDescription,
      },
    ],

    projects: [
      {
        projectSubject: projectSubject,
        part: part,
        link: link,
        startMonth: startMonth,
        startYear: startYear,
        endMonth: endMonth,
        endYear: endYear,
        // projectSkillStacks: [
        //   {
        //     skillStackId: tags,
        //   },
        // ],
        description: proDescription,
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
    } else if (name === 'admissionmonth') {
      setAdmissionMonth(value);
    } else if (name === 'admissionyear') {
      setAdmissionYear(value);
    } else if (name === 'graduationmonth') {
      setGraduationMonth(value);
    } else if (name === 'graduationyear') {
      setGraduationYear(value);
    } else if (name === 'joinmonth') {
      setJoinMonth(value);
    } else if (name === 'joinyear') {
      setJoinYear(value);
    } else if (name === 'retirementmonth') {
      setRetirementMonth(value);
    } else if (name === 'retirementyear') {
      setRetirementYear(value);
    } else if (name === 'startmonth') {
      setStartMonth(value);
    } else if (name === 'startyear') {
      setStartYear(value);
    } else if (name === 'endmonth') {
      setEndMonth(value);
    } else if (name === 'endyear') {
      setEndYear(value);
    } else if (name === 'degree') {
      setDegree(value);
    } else if (name === 'major') {
      setMajor(value);
    } else if (name === 'schoolname') {
      setSchoolName(value);
    } else if (name === 'edudescription') {
      setEduDescription(value);
    } else if (name === 'companyname') {
      setCompanyName(value);
    } else if (name === 'duty') {
      setDuty(value);
    } else if (name === 'careersdevelopmentjob') {
      setCareersDevelopmentJob(value);
    } else if (name === 'jobdescription') {
      setJobDescription(value);
    } else if (name === 'projectsubject') {
      setProjectSubject(value);
    } else if (name === 'part') {
      setPart(value);
    } else if (name === 'protags') {
      setTags(value);
    } else if (name === 'prodescription') {
      setProDescription(value);
    } else if (name === 'link') {
      setLink(value);
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
      </div>
      <div className="body">
        <div>
          <span>경력 및 프로젝트</span>
          <hr></hr>
        </div>
        <div className="test2">
          <div className="name1">
            <span>학위</span>
            <input
              maxLength={30}
              name="degree"
              type="text"
              value={degree}
              onChange={onChange}
            ></input>
          </div>

          <div className="name2">
            <span>전공</span>
            <input
              maxLength={30}
              name="major"
              type="text"
              value={major}
              onChange={onChange}
            ></input>
          </div>
        </div>
        <div className="school">
          <span>학교</span>
          <input
            maxLength={30}
            name="schoolname"
            type="text"
            value={schoolName}
            onChange={onChange}
          ></input>
        </div>
        <div className="test2">
          <div className="birth2">
            <span>시작일</span>
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="admissionmonth"
                value={admissionMonth}
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
            <FormControl sx={{ m: 1, width: 180 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="admissionyear"
                value={admissionYear}
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
          <div className="birth2">
            <span>종료일</span>
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="graduationmonth"
                value={graduationMonth}
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
            <FormControl sx={{ m: 1, width: 180 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="graduationyear"
                value={graduationYear}
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
        </div>

        <div className="intro">
          <span>설명</span>
          <textarea
            name="edudescription"
            type="text"
            value={eduDescription}
            onChange={onChange}
          ></textarea>
        </div>
        <div>
          <StyledDeleteButton>삭제하기</StyledDeleteButton>
        </div>
        <div>
          <StyledButton>+ 학력 추가하기</StyledButton>
        </div>
      </div>
      <div className="body">
        <div className="test2">
          <div className="name1">
            <span>회사명</span>
            <input
              maxLength={30}
              name="companyname"
              type="text"
              value={companyName}
              onChange={onChange}
            ></input>
          </div>

          <div className="name2">
            <span>직책</span>
            <input
              maxLength={30}
              name="duty"
              type="text"
              value={duty}
              onChange={onChange}
            ></input>
          </div>
        </div>
        <div className="school">
          <span>개발직무</span>
          <input
            maxLength={30}
            name="careersdevelopmentjob"
            type="text"
            value={careersDevelopmentJob}
            onChange={onChange}
          ></input>
        </div>
        <div className="test2">
          <div className="birth2">
            <span>시작일</span>
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="joinmonth"
                value={joinMonth}
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
            <FormControl sx={{ m: 1, width: 180 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="joinyear"
                value={joinYear}
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
          <div className="birth2">
            <span>종료일</span>
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="retirementmonth"
                value={retirementMonth}
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
            <FormControl sx={{ m: 1, width: 180 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="retirementyear"
                value={retirementYear}
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
        </div>
        <div className="intro">
          <span>설명</span>
          <textarea
            name="jobdescription"
            type="text"
            value={jobDescription}
            onChange={onChange}
          ></textarea>
        </div>
        <div>
          <StyledDeleteButton>삭제하기</StyledDeleteButton>
        </div>
        <div>
          <StyledButton>+ 경력 추가하기</StyledButton>
        </div>
      </div>
      <div className="body">
        <div className="test2">
          <div className="name1">
            <span>프로젝트 명</span>
            <input
              maxLength={30}
              name="projectsubject"
              type="text"
              value={projectSubject}
              onChange={onChange}
            ></input>
          </div>

          <div className="name2">
            <span>역할</span>
            <input
              maxLength={30}
              name="part"
              type="text"
              value={part}
              onChange={onChange}
            ></input>
          </div>
        </div>
        <div className="school">
          <span>프로젝트 링크</span>
          <input
            name="link"
            type="text"
            value={link}
            onChange={onChange}
          ></input>
          <span>기술스택</span>
          <TagInput tags={tags} setTags={setTags} />
        </div>
        <div className="test2">
          <div className="birth2">
            <span>시작일</span>
            <FormControl sx={{ m: 1, width: 115, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="startmonth"
                value={startMonth}
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
            <FormControl sx={{ m: 1, width: 170 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="startyear"
                value={startYear}
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
          <div className="birth2">
            <span>종료일</span>
            <FormControl sx={{ m: 1, width: 115, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="endmonth"
                value={endMonth}
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
            <FormControl sx={{ m: 1, width: 170 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="endyear"
                value={endYear}
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
        </div>

        <div className="intro">
          <span>설명</span>
          <textarea
            name="prodescription"
            type="text"
            value={proDescription}
            onChange={onChange}
          ></textarea>
        </div>
        <div>
          <StyledDeleteButton>삭제하기</StyledDeleteButton>
        </div>
        <div>
          <StyledButton>+ 프로젝트 추가하기</StyledButton>
        </div>
        <StyledButton onClick={handleClickSave}>수정 완료</StyledButton>
      </div>
    </Container>
  );
};

export default EditCv;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 5rem auto;
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
  .name1 {
    display: flex;
    flex-direction: column;
    width: 50%;
    input {
      width: 24rem;
    }
  }
  .name2 {
    display: flex;
    flex-direction: column;
    width: 50%;
    input {
      width: 24.5rem;
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
      width: 100%;
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

const StyledDeleteButton = styled.button`
  width: 6rem;
  height: 2rem;
  font-size: 0.8rem;
  font-weight: bold;
  cursor: pointer;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: white;
  border: 1px solid gray;
  color: black;

  &:hover {
    background-color: var(--puple300);
  }
`;
