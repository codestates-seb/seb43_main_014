import styled from 'styled-components';
import React from 'react';

const TagInput = ({ tags, setTags }) => {
  const addTag = (e) => {
    if (
      tags.length < 3 &&
      !tags.includes(e.target.value) &&
      e.target.value !== ''
    ) {
      setTags([...tags, e.target.value]);
      e.target.value = '';
    } else if (e.target.value === '') {
      e.target.value = '';
    }
  };

  const removeTag = (idx) => {
    setTags(
      tags.filter((el) => {
        return el !== tags[idx];
      }),
    );
  };
  return (
    <TagsInputWrapper>
      <Input
        type="text"
        onKeyUp={(e) => {
          if (e.key === 'Enter') {
            addTag(e);
          }
        }}
        placeholder="기술스택을 입력하세요. ex) REACT, JAVASCRIPT, etc"
      />
      <ul id="tags">
        {tags &&
          tags.map((i, idx) => (
            <li key={idx} className="tag">
              <span className="tag-title">{i}</span>
              <span
                role="presentation" //이미지라는 사실을 숨김
                className="close"
                onClick={() => {
                  removeTag(idx);
                }}
              >
                x
              </span>
            </li>
          ))}
      </ul>
    </TagsInputWrapper>
  );
};

const TagsInputWrapper = styled.div`
  width: 100%;
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;

  > ul {
    display: flex;
    flex-wrap: wrap;
    padding: 0;

    > li {
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 1.4rem;
      list-style: none;
      padding: 0.2rem 0.4rem;
      margin-right: 0.5rem;
      margin-bottom: 1rem;
      border-radius: 2rem;
      background: white;
      box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

      .close {
        color: black;
        margin-left: 0.5rem;
        font-size: 1rem;
        font-weight: 300;
        padding: 0 0.2rem;
        cursor: pointer;
      }
    }
  }
`;
const Input = styled.input`
  width: 100%;
  &::placeholder {
    color: gray;
  }
`;

export default TagInput;
