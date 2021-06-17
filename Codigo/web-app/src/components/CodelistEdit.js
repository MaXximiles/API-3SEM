import React, { useEffect, useState } from "react";
import Dropdown from "./Dropdown";
import restAPI from "../apis/restAPI";

const CodelistEdit = ({ onSubmit, dataEntry, docId }) => {
  const [search, setSearch] = useState("");
  const [section, setSection] = useState("");
  const [subsection, setSubsection] = useState("");
  const [block, setBlock] = useState("");
  const [blockCode, setBlockCode] = useState("");
  const [blockNum, setBlockNum] = useState("");
  const [traceOptions, setTraceOptions] = useState([]);
  const [selectedTrace, setSelectedTrace] = useState([]);
  const [prevSelectedTrace, setPrevSelectedTrace] = useState(null);
  const [tagOptions, setTagOptions] = useState([]);
  const [selectedTag, setSelectedTag] = useState([]);
  const [prevSelectedTag, setPrevSelectedTag] = useState(null);

  useEffect(() => {
    const getCodelistTraces = async () => {
      const { data } = await restAPI.get(
        `/codelist/tracosbloco?blocoid=${dataEntry.codelistid}`
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

    const getCodelistTags = async () => {
      const { data } = await restAPI.get(
        `/tag/tagsblocos?blocoid=${dataEntry.codelistid}`
      );

      console.log(data);

      const options = data.map((value) => {
        return { value: value.tagId, label: value.tagNome };
      });

      console.log(data, options);

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

    dataEntry && getTagOptions();
    dataEntry && getCodelistTags();
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
        prevSelectedTrace.forEach((value) => {
          if (!selectedTrace.includes(value)) {
            var removeTraces = {
              blocoid: dataEntry.codelistid,
              tracoid: value.value,
            };

            console.log("removeTraces: ", removeTraces);
            restAPI.post(`/relacao_bloco_traco/delete`, removeTraces);
          }
        });

        selectedTrace.forEach((value) => {
          if (!prevSelectedTrace.includes(value)) {
            var addTraces = {
              blocoid: dataEntry.codelistid,
              tracoid: value.value,
            };

            console.log("addTraces: ", addTraces);
            restAPI.post(`/relacao_bloco_traco/`, addTraces);
          }
        });

        // if (addTraces !== {}) {
        //   const response = await restAPI.post(
        //     `/relacao_bloco_traco/`,
        //     addTraces
        //   );
        //   console.log(response);
        // }

        // if (removeTraces !== {}) {
        //   await restAPI.post(`/relacao_bloco_traco/delete`, removeTraces);
        // }

        setPrevSelectedTrace(selectedTrace);
      }
    };

    const toggleTags = async () => {
      if (selectedTag !== prevSelectedTag && dataEntry && prevSelectedTag) {
        prevSelectedTag.forEach((value) => {
          if (!selectedTag.includes(value)) {
            var removeTraces = {
              blocoId: dataEntry.codelistid,
              tagId: value.value,
            };

            restAPI.post(`/tagbloco/delete`, removeTraces);
          }
        });

        selectedTag.forEach((value) => {
          if (!prevSelectedTag.includes(value)) {
            var addTraces = {
              blocoId: dataEntry.codelistid,
              tagId: value.value,
            };

            restAPI.post(`/tagbloco/`, addTraces);
          }
        });

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
      setSection(dataEntry.codelistsecao);
      setSubsection(dataEntry.codelistsubsecao);
      setBlock(dataEntry.codelistnomebloco);
      setBlockCode(dataEntry.codelistcodebloco);
      setBlockNum(dataEntry.codelistnumbloco);
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

  const submitSearch = async (event) => {
    if (event.key === "Enter") {
      const { data } = await restAPI.get(
        `/codelist/blocosnome?bloconome=${search}`
      );

      const blockInfo = data[0];

      setSection(blockInfo.codelistsecao);
      setSubsection(blockInfo.codelistsubsecao);
      setBlock(blockInfo.codelistnomebloco);
      setBlockCode(blockInfo.codelistcodebloco);
      setBlockNum(blockInfo.codelistnumbloco);
    }
  };

  const submit = async () => {
    const submitedEntry = {
      codelistcodebloco: blockCode,
      codelistnomebloco: block,
      codelistnumbloco: blockNum,
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
          {!dataEntry && (
            <React.Fragment>
              <div className="field">
                <label>Localizar Bloco Existente por Nome</label>
                <input
                  type="text"
                  name="search"
                  placeholder="Nome Bloco"
                  value={search}
                  onChange={(e) => setSearch(e.target.value)}
                  onKeyDown={(e) => submitSearch(e)}
                />
              </div>
              <div class="ui divider"></div>
            </React.Fragment>
          )}
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
            <label>Número do Bloco</label>
            <input
              type="text"
              name="block"
              placeholder="Bloco"
              value={blockNum}
              onChange={(e) => setBlockNum(e.target.value)}
            />
          </div>
          <div className="field">
            <label>Código do Bloco</label>
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

export default CodelistEdit;
