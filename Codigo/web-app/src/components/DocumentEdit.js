import React, { useState, useEffect } from "react";
import Dropdown from "./Dropdown";
import restAPI from "../apis/restAPI";

const DocumentEdit = ({ onSubmit, dataEntry }) => {
  const [name, setName] = useState("");
  const [pn, setPn] = useState("");
  const [traceOptions, setTraceOptions] = useState([]);
  const [selectedTrace, setSelectedTrace] = useState([]);
  const [prevSelectedTrace, setPrevSelectedTrace] = useState(null);
  const [tagOptions, setTagOptions] = useState([]);
  const [selectedTag, setSelectedTag] = useState([]);
  const [prevSelectedTag, setPrevSelectedTag] = useState(null);

  useEffect(() => {
    const getDocumentTraces = async () => {
      const { data } = await restAPI.get(
        `/traco_doc/tracodoc?docid=${dataEntry.documentoid}`
      );

      console.log("traços doc", data);

      const options = data.map((value) => {
        return { value: value.tracodocid, label: value.tracodocnome };
      });

      console.log("Generated options", options);

      setSelectedTrace(options);
      setPrevSelectedTrace(options);
    };

    const getTraceOptions = async () => {
      const { data } = await restAPI.get(`/traco_doc/`);

      const options = data.map((value) => {
        return { value: value.tracodocid, label: value.tracodocnome };
      });

      setTraceOptions(options);
    };

    const getDocumentTags = async () => {
      const { data } = await restAPI.get(
        `/tag/tagsdocumentos?docid=${dataEntry.documentoid}`
      );

      const options = data.map((value) => {
        return { value: value.tagId, label: value.tagNome };
      });

      console.log("tagoptions", options);

      setSelectedTag(options);
      setPrevSelectedTag(options);
    };

    const getTagOptions = async () => {
      const { data } = await restAPI.get(`/tag/`);

      const options = data.map((value) => {
        return { value: value.tagId, label: value.tagNome };
      });

      setTagOptions(options);
    };

    getTraceOptions();
    getTagOptions();
    dataEntry && getDocumentTraces();
    dataEntry && getDocumentTags();
  }, [dataEntry]);

  useEffect(() => {
    const toggleTraces = async () => {
      if (
        selectedTrace !== prevSelectedTrace &&
        dataEntry &&
        prevSelectedTrace
      ) {
        prevSelectedTrace.forEach((value) => {
          if (!selectedTrace.includes(value)) {
            var removeTraces = {
              docid: dataEntry.documentoid,
              tracoid: value.value,
            };

            restAPI.post(`/reldoctraco/delete`, removeTraces);
          }
        });

        selectedTrace.forEach((value) => {
          if (!prevSelectedTrace.includes(value)) {
            var addTraces = {
              docid: dataEntry.documentoid,
              tracoid: value.value,
            };

            restAPI.post(`/reldoctraco/`, addTraces);
          }
        });

        // if (addTraces !== {}) {
        //   console.log("traces to be added", addTraces);
        //   const response = await console.log(response);
        // }

        setPrevSelectedTrace(selectedTrace);
      }
    };

    const toggleTags = async () => {
      if (selectedTag !== prevSelectedTag && dataEntry && prevSelectedTag) {
        prevSelectedTag.forEach((value) => {
          if (!selectedTag.includes(value)) {
            var removeTraces = {
              documentoId: dataEntry.documentoid,
              tagId: value.value,
            };

            restAPI.post(`/tagdocumento/delete`, removeTraces);
          }
        });

        selectedTag.forEach((value) => {
          if (!prevSelectedTag.includes(value)) {
            var addTraces = {
              documentoId: dataEntry.documentoid,
              tagId: value.value,
            };

            restAPI.post(`/tagdocumento/`, addTraces);
          }
        });

        // if (addTraces !== {}) {
        //   console.log("Tags to be added", addTraces);
        //   const response = await restAPI.post(`/tagdocumento/`, addTraces);
        //   console.log(response);
        // }

        // if (removeTraces !== {}) {
        //   console.log("Tags to be removed", removeTraces);
        //   await restAPI.post(`/tagdocumento/delete`, removeTraces);
        // }

        setPrevSelectedTag(selectedTag);
      }
    };

    toggleTraces();
    toggleTags();
  }, [
    selectedTrace,
    prevSelectedTrace,
    selectedTag,
    prevSelectedTag,
    dataEntry,
  ]);

  useEffect(() => {
    if (dataEntry) {
      setName(dataEntry.documentonome);
      setPn(dataEntry.documentopn);
    } else {
      clearFields();
    }
  }, [dataEntry]);

  const clearFields = () => {
    setName("");
    setPn("");
    setSelectedTrace([]);
  };

  const submit = async () => {
    setPrevSelectedTrace(null);

    const submitedEntry = {
      documentonome: name,
      documentopn: pn,
      documentotraco: selectedTrace,
    };

    if (dataEntry) {
      submitedEntry["documentocaminho"] = dataEntry.documentocaminho;
    }

    if (dataEntry) {
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
            <label>Nome</label>
            <input
              type="text"
              name="section"
              placeholder="Nome"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div className="field">
            <label>PN</label>
            <input
              type="text"
              name="subsection"
              placeholder="PN"
              value={pn}
              onChange={(e) => setPn(e.target.value)}
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
          <div className="field">
            {dataEntry && (
              <Dropdown
                label="Tags"
                defaultText="Selecione um ou mais tags"
                options={tagOptions}
                selected={selectedTag}
                onSelectedChange={setSelectedTag}
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

export default DocumentEdit;
