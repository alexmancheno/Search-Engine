import React,  {Component} from 'react';
import IndexedPages from './IndexedPages/IndexedPages';

export default class AdminView extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <IndexedPages/>
                {/* <PagesToBeIndexed pagesToBeIndexed={this.state.pagesToBeIndexed} />
                <searchQueriesAndResultCount searchQueriesAndResultCount={this.state.searchQueriesAndResultCount} /> */}
            </div>
        )
    }
}