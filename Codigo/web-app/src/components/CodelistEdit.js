import React, { useEffect, useState } from "react";

const CodelistEdit = ({ onSubmit, dataEntry, docId }) => {
  const [section, setSection] = useState("");
  const [subsection, setSubsection] = useState("");
  const [block, setBlock] = useState("");
  const [blockCode, setBlockCode] = useState("");

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
    const submitedEntry = {
      codelistcodebloco: blockCode,
      codelistnomebloco: block,
      codelistsecao: section,
      codelistsubsecao: subsection,
      documentoid: docId,
    };

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
