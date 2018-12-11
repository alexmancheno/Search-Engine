import React, { Component } from 'react';

export default class SearchResults extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        // console.log(this.props.searchResults);
        return (
            <div className="list-group">
                {this.props.searchResults.map(result => (
                    <a key={result.toString()} href={result} className="list-group-item list-group-item-action">{result}</a>
                ))}
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#" onClick={e => this.props.handlePageChange(1)}>1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
            </div>
        )
    }
}