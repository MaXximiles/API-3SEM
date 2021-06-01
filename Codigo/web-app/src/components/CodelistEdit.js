import React, { useEffect, useState } from "react";
import Dropdown from "./Dropdown";
import restAPI from "../apis/restAPI";

const CodelistEdit = ({ onSubmit, dataEntry, docId }) => {
  const [section, setSection] = useState("");
  const [subsection, setSubsection] = useState("");
  const [block, setBlock] = useState("");
  const [blockCode, setBlockCode] = useState("");
  const [traceOptions, setTraceOptions] = useState([]);
  const [selectedTrace, setSelectedTrace] = useState([]);
  const [prevSelectedTrace, setPrevSelectedTrace] = useState(null);

  useEffect(() => {
    const getCodelistTraces = async () => {
      const { data } = await restAPI.get(
        `/traco_doc/tracodoc?docid=${dataEntry.documentoid}`
      );

      const options = data.map((value) => {
        return { value: value.tracodocid, label: value.tracodocnome };
      });

      setSelectedTrace(options);
      setPrevSelectedTrace(options);
    };

    const getTraceOptions = async () => {
      const { data } = await restAPI.get(
        `/traco_doc/tracodoc?docid=${dataEntry.documentoid}`
      );

      const options = data.map((value) => {
        return { value: value.tracodocid, label: value.tracodocnome };
      });

      setTraceOptions(options);
    };

    dataEntry && getTraceOptions();
    dataEntry && getCodelistTraces();
  }, [dataEntry]);

  useEffect(() => {
    const toggleTraces = async () => {
      if (
        selectedTrace !== prevSelectedTrace &&
        dataEntry &&
        prevSelectedTrace
      ) {
        var addTraces = {};
        selectedTrace.forEach((value) => {
          if (!prevSelectedTrace.includes(value)) {
            addTraces = {
              blocoid: dataEntry.codelistid,
              tracoid: value.value,
            };
          }
        });

        if (addTraces !== {}) {
          const response = await restAPI.post(
            `/relacao_bloco_traco/`,
            addTraces
          );
          console.log(response);
        }

        var removeTraces = {};
        prevSelectedTrace.forEach((value) => {
          if (!selectedTrace.includes(value)) {
            removeTraces = {
              blocoid: dataEntry.codelistid,
              tracoid: value.value,
            };
          }
        });

        if (removeTraces !== {}) {
          await restAPI.post(`/relacao_bloco_traco/delete`, removeTraces);
        }

        setPrevSelectedTrace(selectedTrace);
      }
    };

    if (selectedTrace !== prevSelectedTrace) {
      toggleTraces();
    }
  }, [selectedTrace, prevSelectedTrace, dataEntry]);

  useEffect(() => {
    if (dataEntry) {
      setSection(dataEntry.codelistsecao);
      setSubsection(dataEntry.codelistsubsecao);
      setBlock(dataEntry.codelistnomebloco);
      setBlockCode(dataEntry.codelistcodebloco);
    } else {
      clearFields();
    }
  }, [dataEntry]);

  const clearFields = () => {
    setSection("");
    setSubsection("");
    setBlock("");
    setBlockCode("");
  };

  const submit = async () => {
    setPrevSelectedTrace(null);

    const submitedEntry = {
      codelistcodebloco: blockCode,
      codelistnomebloco: block,
      codelistsecao: section,
      codelistsubsecao: subsection,
      documentoid: docId,
    };

    if (dataEntry) {
      submitedEntry["codelistcaminho"] = dataEntry.codelistcaminho;
      await onSubmit(submitedEntry, dataEntry);
    } else {
      await onSubmit(submitedEntry);
    }

    clearFields();
  };

  return (
    <React.Fragment>
      <div className="content">
        <form className="ui form">
          <div className="field">
            <label>Seção</label>
            <input
              type="text"
              name="section"
              placeholder="Seção"
              value={section}
              onChange={(e) => setSection(e.target.value)}
            />
          </div>
          <div className="field">
            <label>Subseção</label>
            <input
              type="text"
              name="subsection"
              placeholder="Subseção"
              value={subsection}
              onChange={(e) => setSubsection(e.target.value)}
            />
          </div>
          <div className="field">
            <label>Bloco</label>
            <input
              type="text"
              name="block"
              placeholder="Bloco"
              value={block}
              onChange={(e) => setBlock(e.target.value)}
            />
          </div>
          <div className="field">
            <label>Código do bloco</label>
            <input
              type="text"
              name="blockCode"
              placeholder="Código"
              value={blockCode}
              onChange={(e) => setBlockCode(e.target.value)}
            />
          </div>
          <div className="field">
            {dataEntry && (
              <Dropdown
                label="Traço"
                defaultText="Selecione um ou mais traços"
                options={traceOptions}
                selected={selectedTrace}
                onSelectedChange={setSelectedTrace}
              />
            )}
          </div>
        </form>
      </div>
      <div className="actions">
        <div className="ui positive right labeled icon button" onClick={submit}>
          Confirmar
          <i className="checkmark icon"></i>
        </div>
      </div>
    </React.Fragment>
  );
};

export default CodelistEdit;
