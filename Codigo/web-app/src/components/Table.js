import "./Table.css";
import React, { useEffect, useRef, useState } from "react";
import Modal from "./Modal";

const Table = ({ data }) => {
  const [columnStyle, setColumnStyle] = useState([]);
  const [isCreatingNew, setIsCreatingNew] = useState(false);
  const thead = useRef();
  const tbody = useRef();

  useEffect(() => {
    if (tbody.current.children[0]) {
      const firstRow = tbody.current.children[0];

      var bodyCellsSize = [];
      for (const child of firstRow.children) {
        bodyCellsSize = [...bodyCellsSize, child.clientWidth];
      }

      var headerCellsSize = [];
      for (const child of thead.current.children) {
        headerCellsSize = [...headerCellsSize, child.clientWidth];
      }

      const columnsSize = headerCellsSize.map((cellSize, index) => {
        if (index === 0 || index === headerCellsSize.length - 1) {
          if (index === 0) {
            return bodyCellsSize[index] > cellSize
              ? { minWidth: `${bodyCellsSize[index]}px` }
              : { minWidth: `${cellSize}px` };
          } else {
            return { width: "100%" };
          }
        } else {
          return bodyCellsSize[index] > cellSize
            ? { minWidth: `${bodyCellsSize[index] + 1}px` }
            : { minWidth: `${cellSize + 1}px` };
        }
      });

      setColumnStyle(columnsSize);
    }
  }, [data]);

  const rendredTableHead = data.length
    ? Object.keys(data[0]).map((key, index) => {
        return (
          <th key={key} style={columnStyle[index]}>
            {key}
          </th>
        );
      })
    : null;

  const renderedTableBody = data.map((item, rowIndex) => {
    const renderedTableCells = Object.entries(item).map((value, index) => {
      return (
        <td key={value[1]} data-label={value[0]} style={columnStyle[index]}>
          {value[1]}
        </td>
      );
    });

    return <tr key={rowIndex}>{renderedTableCells}</tr>;
  });

  const renderedEditModal = (
    <Modal
      title="Criando Novo Bloco"
      isOpen={isCreatingNew}
      setOpen={setIsCreatingNew}
    >
      
    </Modal>
  );

  return (
    <div className="Table">
      {renderedEditModal}
      <table className="ui celled table unstackable">
        <thead>
          <tr ref={thead}>{rendredTableHead}</tr>
        </thead>
        <tbody ref={tbody}>{renderedTableBody}</tbody>
        <tfoot className="full-width">
          <tr>
            <th colSpan={data.length ? Object.keys(data[0]).length : 0}>
              <div
                className="ui right floated small primary labeled icon button"
                onClick={() => setIsCreatingNew(true)}
              >
                <i className="plus icon"></i>
                Adicionar Bloco
              </div>
            </th>
          </tr>
        </tfoot>
      </table>
    </div>
  );
};

export default Table;
