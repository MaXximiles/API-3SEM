import React, { useEffect, useState } from "react";
import DropdownSimple from "./DropdownSimple";

import restAPI from "../apis/restAPI";

const CodelistGenerate = ({ generate, onSubmit, docId }) => {
  const [options, setOptions] = useState([]);
  const [selectedOption, setSelectedOption] = useState({});

  useEffect(() => {
    const getRevisions = async () => {
      const { data } = await restAPI.get(`/codelist/revisoes?docid=${docId}`);

      var resultOptions = [];
      data.forEach((el) => {
        resultOptions = [...resultOptions, { label: el, value: el }];
      });

      console.log("result options", resultOptions);
      setOptions(resultOptions);
    };

    getRevisions();
  }, [docId]);

  return (
    <React.Fragment>
      <div className="content">
        <form className="ui form">
          <div className="field">
            <DropdownSimple
              label="Selecione uma revisão"
              defaultText="Selecione uma revisão"
              options={options}
              selected={selectedOption}
              onSelectedChange={setSelectedOption}
            />
          </div>
        </form>
      </div>
      <div className="actions">
        <div
          className={`ui positive right labeled icon button ${
            selectedOption.label ? "" : "disabled"
          }`}
          onClick={() => onSubmit(generate, selectedOption.value)}
        >
          Confirmar
          <i className="checkmark icon"></i>
        </div>
      </div>
    </React.Fragment>
  );
};

export default CodelistGenerate;
